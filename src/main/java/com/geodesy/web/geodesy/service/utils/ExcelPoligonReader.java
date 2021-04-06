package com.geodesy.web.geodesy.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geodesy.web.geodesy.model.poligon.Poligon;
import com.geodesy.web.geodesy.model.poligon.PoligonData;
import com.geodesy.web.geodesy.model.poligon.PoligonMove;
import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class ExcelPoligonReader {

    public static final int MIN_NUMBER_OF_CELLS = 5;
    public static final String REPER = "Reper";
    public static final String HEIGHT_M = "Height,m";
    public static final String POLIGON_NUMBER = "#";
    public static final String STEPS = "Steps";
    public static final String EXCEEDING_M = "Exceeding,m";
    public static final String NUMBER_OF_STATION = "Number of station";
    public static final String LENGTH_KM = "Length,km";
    private static final Logger LOGGER = Logger.getLogger(ExcelPoligonReader.class);
    private static final Map<String, BiConsumer<Cell, PoligonData>> poligonDataCreators = new HashMap<>();

    static {
        poligonDataCreators.put(REPER, ExcelPoligonReader::setReperName);
        poligonDataCreators.put(HEIGHT_M, ExcelPoligonReader::setReperHeight);
        poligonDataCreators.put(POLIGON_NUMBER, ExcelPoligonReader::addPoligon);
        poligonDataCreators.put(STEPS, ExcelPoligonReader::setMoveName);
        poligonDataCreators.put(EXCEEDING_M, ExcelPoligonReader::setMoveDifference);
        poligonDataCreators.put(NUMBER_OF_STATION, ExcelPoligonReader::setStationCount);
        poligonDataCreators.put(LENGTH_KM, ExcelPoligonReader::setMoveDistance);

    }

    private static <T> T getLast(List<T> collection, Supplier<T> orElseGet) {
        if (isEmpty(collection)) {
            collection.add(orElseGet.get());
        }
        return collection.get(collection.size() - 1);
    }

    private static <T> T getLast(List<T> collection) {
        return getLast(collection, () -> null);
    }

    private static void setReperName(Cell cell, PoligonData poligonData) {
        PoligonReper last = getLast(poligonData.getReperList(), PoligonReper::new);
        last.setName(cell.getStringCellValue());
    }

    private static void setReperHeight(Cell cell, PoligonData poligonData) {
        PoligonReper last = getLast(poligonData.getReperList(), PoligonReper::new);
        if (last.getHeight() == null) {
            last.setHeight(cell.getNumericCellValue());
        }
    }

    private static void addPoligon(Cell cell, PoligonData poligonData) {
        Poligon last = getLast(poligonData.getPoligonList());
        String name = String.valueOf(cell.getNumericCellValue());
        if (last == null || !name.equals(last.getName())) {
            poligonData.addPoligon(new Poligon().setName(name));
        }
    }

    private static void setMoveName(Cell cell, PoligonData poligonData) {
        getLast(poligonData.getPoligonList(), Poligon::new).addPoligonMove(new PoligonMove().setName(cell.getStringCellValue()));
    }

    private static void setMoveDifference(Cell cell, PoligonData poligonData) {
        PoligonMove poligonMove = getLast(getLast(poligonData.getPoligonList(), Poligon::new).getPoligonMoves(), PoligonMove::new);
        poligonMove.setDifference(cell.getNumericCellValue());
    }

    private static void setMoveDistance(Cell cell, PoligonData poligonData) {
        PoligonMove poligonMove = getLast(getLast(poligonData.getPoligonList(), Poligon::new).getPoligonMoves(), PoligonMove::new);
        poligonMove.setDistance(cell.getNumericCellValue());
    }

    private static void setStationCount(Cell cell, PoligonData poligonData) {
        PoligonMove poligonMove = getLast(getLast(poligonData.getPoligonList(), Poligon::new).getPoligonMoves(), PoligonMove::new);
        poligonMove.setStationCount((int) cell.getNumericCellValue());
    }

    public PoligonData getPoligonData(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return null;
        return read(multipartFile);
    }

    public PoligonData read(MultipartFile multipartFile) {
        PoligonData poligonData = new PoligonData();
        try (
                OPCPackage fs = OPCPackage.open(multipartFile.getInputStream());
                XSSFWorkbook wb = new XSSFWorkbook(fs)
        ) {
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            XSSFRow firstRow = sheet.getRow(sheet.getFirstRowNum());
            Iterator<Cell> firstCellsIterator = firstRow.cellIterator();
            Map<Integer, String> columnIndexToColumnName = new HashMap<>();
            while (firstCellsIterator.hasNext()) {
                Cell next = firstCellsIterator.next();
                poligonDataCreators.keySet().stream().filter(s -> s.equalsIgnoreCase(next.getStringCellValue())).findAny().ifPresent(s -> {
                    columnIndexToColumnName.put(next.getColumnIndex(), s);
                });
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getPhysicalNumberOfCells() < MIN_NUMBER_OF_CELLS) {
                    break;
                }
                if (firstRow.equals(row)) {
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (columnIndexToColumnName.containsKey(cell.getColumnIndex())) {
                        poligonDataCreators.get(columnIndexToColumnName.get(cell.getColumnIndex())).accept(cell, poligonData);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        poligonData.setPoligonList(poligonData.getPoligonList().stream().filter(Objects::nonNull).collect(Collectors.toList()));
        try {
            System.err.println(new ObjectMapper().writeValueAsString(poligonData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return poligonData;
    }

}
