package com.geodesy.web.geodesy.service.utils;

import com.geodesy.web.geodesy.model.approximation.ApproximationMove;
import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.model.approximation.ApproximationReper;
import com.geodesy.web.geodesy.model.poligon.Poligon;
import com.geodesy.web.geodesy.model.poligon.PoligonData;
import com.geodesy.web.geodesy.model.poligon.PoligonMove;
import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ExcelPoligonReader {

    private static final Logger LOGGER = Logger.getLogger(ExcelPoligonReader.class);


    public static void main(String[] args) {
//        read();
    }

    public PoligonData getPoligonData(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return null;
        PoligonData poligonData = new PoligonData();
        read(multipartFile, poligonData);
        return poligonData;
    }

    public void read(MultipartFile multipartFile, PoligonData poligonData) {
        try {
//            todo list
            poligonData.setReperList(new ArrayList<>());
            POIFSFileSystem fs = new POIFSFileSystem(multipartFile.getInputStream());
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            Poligon poligon = null;
            for (int r = 1; r < rows; r++) {
                row = sheet.getRow(r);

                //            todo list create element
                ApproximationReper tempApproximationReper = new ApproximationReper();
                ApproximationMove tempApproximationMove = new ApproximationMove();
                if (row != null) {
                    Double index = -1d;
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Reper".toLowerCase())) {
                                if (r > 1)
                                    continue;
                                poligonData.setReperList(Arrays.asList(new PoligonReper().setName(cell.getStringCellValue())));
                                LOGGER.info("ApproximationReper :row:[" + r + "]cell:[" + (cell.getStringCellValue()) + "]");
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Height,m".toLowerCase())) {
                                if (r > 1)
                                    continue;
                                poligonData.getReperList().get(0).setHeight(cell.getNumericCellValue());
                                LOGGER.info("Steps :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("#".toLowerCase())) {
                                if (index == -1 || index != cell.getNumericCellValue()) {
                                    if (index != cell.getNumericCellValue())
                                        poligonData.getPoligonList().add(poligon);

                                    index = cell.getNumericCellValue();
                                    poligon = new Poligon().setPoligonMoves(new ArrayList<>());


                                }
                                poligon.getPoligonMoves().add(new PoligonMove());
                                //todo
                                LOGGER.info("Steps :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Steps".toLowerCase())) {

                                poligon.getPoligonMoves().get(poligon.getPoligonMoves().size() - 1).setName(cell.getStringCellValue());
                                LOGGER.info("Exceeding,m :row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Exceeding,m".toLowerCase())) {

                                poligon.getPoligonMoves().get(poligon.getPoligonMoves().size() - 1).setDifference(cell.getNumericCellValue());
                                LOGGER.info("Number of station :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Number of station".toLowerCase())) {

                                poligon.getPoligonMoves().get(poligon.getPoligonMoves().size() - 1).setStationCount(Integer.valueOf(cell.getNumericCellValue() + ""));
                                LOGGER.info("Length,km :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Length,km".toLowerCase())) {
//todo
                                poligon.getPoligonMoves().get(poligon.getPoligonMoves().size() - 1).setDistance(cell.getNumericCellValue());
                                LOGGER.info("Length,km :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                            }


//                            if (cell.getCellTypeEnum().equals(CellType.STRING))
//                                LOGGER.info("row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");
//                            else if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                                LOGGER.info("row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                        }
                    }

                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

}