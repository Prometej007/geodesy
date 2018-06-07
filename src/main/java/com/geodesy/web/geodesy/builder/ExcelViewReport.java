package com.geodesy.web.geodesy.builder;

import com.geodesy.web.geodesy.dto.PointDto;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelViewReport extends AbstractXlsView {


    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

//        System.out.println(model.get("tickets"));
//        System.out.println("--------------------");
//        System.out.println("--------------------");
//        model.keySet().stream().forEach(s -> {
//            System.out.println(s);
//        });
//        System.out.println("--------------------");
//        System.out.println("--------------------");
//        System.out.println();

        @SuppressWarnings("unchecked")
//        EventShortDTO event =  ;

                String fileName = String.valueOf(model.get("name"));
        List<PointDto> pointDto = (List<PointDto>) model.get("pointDto");
        Long approximationDtoLength = (Long) model.get("approximationDtoLength");

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
        header.createCell(0).setCellValue("№");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("ходи");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("назви вузлових реперів");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("висоти вузлових реперів");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("суми перевищень");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("ваги Pi,J");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("ваги P`i,J");
        header.getCell(6).setCellStyle(style);
        Integer counter = 0;
        for (int i = 1; i <= approximationDtoLength; i++) {
            header.createCell(i + 6).setCellValue(i);
            header.getCell(i + 6).setCellStyle(style);
            counter += 1;
        }

        header.createCell((int) (approximationDtoLength + 1 + 6)).setCellValue("V m");
        header.getCell((int) (approximationDtoLength + 1 + 6)).setCellStyle(style);

        header.createCell((int) (approximationDtoLength + 2 + 6)).setCellValue("P`v m");
        header.getCell((int) (approximationDtoLength + 2 + 6)).setCellStyle(style);

        header.createCell((int) (approximationDtoLength + 3 + 6)).setCellValue("P`v m2");
        header.getCell((int) (approximationDtoLength + 3 + 6)).setCellStyle(style);
        int indexRow = 1;

        for (PointDto ticket :
                pointDto) {
            Row userRow = sheet.createRow(indexRow++);
            userRow.createCell(0).setCellValue(ticket.getNumber());
            for (int i = 0; i < ticket.getPointOne().size(); i++) {
                userRow.createCell(1).setCellValue(ticket.getPointOne().get(i).getNameMuve());
                userRow.createCell(2).setCellValue(ticket.getPointOne().get(i).getNameReper());
                userRow.createCell(3).setCellValue(ticket.getPointOne().get(i).getHeight());
                userRow.createCell(4).setCellValue(ticket.getPointOne().get(i).getSum());
                userRow.createCell(5).setCellValue(ticket.getPointOne().get(i).getWeight());
                userRow.createCell(6).setCellValue(ticket.getPointOne().get(i).get_weight());

                for (int j = 0; j < ticket.getPointOne().get(i).getApproximationDto().size(); j++) {
                    userRow.createCell(7 + j).setCellValue(ticket.getPointOne().get(i).getApproximationDto().get(j).getValue());
                }
                userRow.createCell((int) (8 - 1 + approximationDtoLength)).setCellValue(ticket.getPointOne().get(i).getCorrection());
                userRow.createCell((int) (9 - 1 + approximationDtoLength)).setCellValue(ticket.getPointOne().get(i).getWeightCorrection());
                userRow.createCell((int) (10 - 1 + approximationDtoLength)).setCellValue(ticket.getPointOne().get(i).getWeightCorrectionCorrection());

                userRow = sheet.createRow(indexRow++);
            }
            userRow.createCell(3).setCellValue("Eh=");
            userRow.createCell(4).setCellValue(ticket.getCheckParams().get(0));
            for (int i = 1; i < ticket.getCheckParams().size(); i++) {
                userRow.createCell((int) (4 + i)).setCellValue(ticket.getCheckParams().get(i));
            }
        }

    }

}