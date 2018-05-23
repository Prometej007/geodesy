package com.geodesy.web.geodesy.service.utils;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static org.aspectj.runtime.internal.Conversions.intValue;

@Service
public class ExcelPoligonReader {

    private static final Logger LOGGER = Logger.getLogger(ExcelPoligonReader.class);


    public static void main(String[] args) {
//        read();
    }

    public CalculationData getCalculationData(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return null;
        CalculationData calculationData = new CalculationData();
        read(multipartFile, calculationData);
        return calculationData;
    }

    public void read(MultipartFile multipartFile, CalculationData calculationData) {
        try {
            calculationData.setMoveList(new ArrayList<>());
            calculationData.setReperList(new ArrayList<>());
//            todo list
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

            for (int r = 1; r < rows; r++) {
                row = sheet.getRow(r);
//            todo list create element
                Reper tempReper = new Reper();
                Move tempMove = new Move();
                if (row != null) {
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("poligon name".toLowerCase()) || sheet.getRow(0).getCell((short) c).getStringCellValue().contains("номер студентського квитка")) {
                                if (cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty()) {

//todo
                                    LOGGER.info("Reper :row:[" + r + "]cell:[" + (cell.getStringCellValue()) + "]");
                                }
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Reper".toLowerCase())) {
//todo
                                LOGGER.info("Height,m :row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Height,m".toLowerCase())) {
//todo
                                LOGGER.info("Steps :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Steps".toLowerCase())) {
//todo
                                LOGGER.info("Exceeding,m :row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Exceeding,m".toLowerCase())) {
//todo
                                LOGGER.info("Number of station :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Number of station".toLowerCase())) {
//todo
                                LOGGER.info("Length,km :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Length,km".toLowerCase())) {
//todo
                                LOGGER.info("Length,km :row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");
                            }


//                            if (cell.getCellTypeEnum().equals(CellType.STRING))
//                                LOGGER.info("row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");
//                            else if (cell.getCellTypeEnum().equals(CellType.NUMERIC))
//                                LOGGER.info("row:[" + r + "]cell:[" + cell.getNumericCellValue() + "]");

                        }
                    }
                    if (tempReper.getHeight() != null && tempReper.getName() != null)
                        calculationData.getReperList().add(tempReper);
                    calculationData.getMoveList().add(tempMove);
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

}