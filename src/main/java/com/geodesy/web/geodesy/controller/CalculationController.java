package com.geodesy.web.geodesy.controller;

import com.geodesy.web.geodesy.builder.ExcelViewReport;
import com.geodesy.web.geodesy.dto.PointDto;
import com.geodesy.web.geodesy.dto.utils.PointDtoParser;
import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.utils.enums.ClassSystem;
import com.geodesy.web.geodesy.service.persistence.CalculationDataService;
import com.geodesy.web.geodesy.service.ConsistentApproximationMethod;
import com.geodesy.web.geodesy.service.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calculation")
public class CalculationController {

    private final PointDtoParser pointDtoParser = new PointDtoParser();
    @Autowired
    private ExcelReader excelReader;
    @Autowired
    private CalculationDataService calculationDataService;
    @Autowired
    private ConsistentApproximationMethod consistentApproximationMethod;

    @PostMapping("/result-1")
    private String calculation1(Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file) {
        CalculationData res = consistentApproximationMethod.calculate(excelReader.getCalculationData(file).setCalculationTypeName(type).setDate(Timestamp.valueOf(LocalDateTime.now())));
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        model.addAttribute("calcs", pointDtoParser.parse(res));
        model.addAttribute("length", res.getApproximationMoveList().get(0).getApproximations().size());
        calculationDataService.save(res);
        return "result";
    }

    @GetMapping("/download/file/{id}")
    private ModelAndView download1(@PathVariable Long id) {
        CalculationData calculationData = calculationDataService.findOne(id);
        List<PointDto> dtoList = pointDtoParser.parse(calculationData);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "calculation_data_" + calculationData.getDate().toString());
        map.put("pointDto", dtoList);
        map.put("approximationDtoLength", (long) calculationData.getApproximationMoveList().get(0).getApproximations().size());
        return new ModelAndView(new ExcelViewReport(), map);
    }

    @PostMapping("/result-2")
    private String calculation2(Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file) {
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        return "result";
    }

    @PostMapping("/result-3")
    private String calculation3(Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file) {
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        return "result";
    }

    @PostMapping("/result-4")
    private String calculation4(Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file) {
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        return "result";
    }

}
