package com.geodesy.web.geodesy.builder;

import com.geodesy.web.geodesy.dto.PointDto;
import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExelViewPoligonReport extends AbstractXlsView {


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

//        System.out.println(model.get("tickets"));
        System.out.println("--------------------");
        System.out.println("--------------------");
        model.keySet().stream().forEach(s -> {
            System.out.println(s);
        });
        System.out.println("--------------------");
        System.out.println("--------------------");
//        System.out.println();

        @SuppressWarnings("unchecked")
//        EventShortDTO event =  ;

                String fileName = String.valueOf(model.get("name"));
        List<PoligonReper> poligonRepers = (List<PoligonReper>) model.get("pointDto");

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\"");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("g");
        sheet.setDefaultColumnWidth(60);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Репере");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Висота");
        header.getCell(1).setCellStyle(style);
        int indexRow = 1;
        for (PoligonReper poligonReper :
                poligonRepers) {
            Row userRow = sheet.createRow(indexRow++);
            userRow.createCell(0).setCellValue(poligonReper.getName());
            userRow.createCell(1).setCellValue(poligonReper.getHeight());
        }

    }

}
