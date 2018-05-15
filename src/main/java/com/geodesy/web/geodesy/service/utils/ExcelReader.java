package com.geodesy.web.geodesy.service.utils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;

public class ExcelReader {

    private static final Logger LOGGER = Logger.getLogger(ExcelReader.class);


    public static void main(String[] args) {
//        read();
    }

    public void read(MultipartFile multipartFile) {
        try {

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
                if (row != null) {
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("номер залікової".toLowerCase()) || sheet.getRow(0).getCell((short) c).getStringCellValue().contains("номер студентського квитка")) {
                                LOGGER.info("nomer zalikovoi :row:[" + r + "]cell:[" + Math.round(cell.getNumericCellValue()) + "]");
                                //            todo list create element set 1
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("Прізвище".toLowerCase())) {

                                LOGGER.info("prizvich :row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");
                                //            todo list create element set 2

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("імя".toLowerCase())) {

                                LOGGER.info("name :row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");
                                //            todo list create element set 3

                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().toLowerCase().contains("група".toLowerCase())) {
                                //            todo list create element set 3
                            } else if (sheet.getRow(0).getCell((short) c).getStringCellValue().equals("5")) {
                                //            todo list create element set 4
                                LOGGER.info("nomer zalikovoi :row:[" + r + "]cell:[" + cell.getStringCellValue() + "]");
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