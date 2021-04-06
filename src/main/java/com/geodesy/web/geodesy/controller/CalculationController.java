package com.geodesy.web.geodesy.controller;

import com.geodesy.web.geodesy.builder.ExcelViewReport;
import com.geodesy.web.geodesy.dto.PointDto;
import com.geodesy.web.geodesy.dto.utils.PointDtoParser;
import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.model.poligon.PoligonData;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.utils.enums.ClassSystem;
import com.geodesy.web.geodesy.service.ConsistentApproximationMethod;
import com.geodesy.web.geodesy.service.PoligonMethod;
import com.geodesy.web.geodesy.service.persistence.CalculationDataService;
import com.geodesy.web.geodesy.service.persistence.PoligonDataService;
import com.geodesy.web.geodesy.service.utils.ExcelPoligonReader;
import com.geodesy.web.geodesy.service.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Controller
@RequestMapping("/calculation")
public class CalculationController {

    private final PointDtoParser pointDtoParser = new PointDtoParser();
    @Autowired
    private ExcelReader excelReader;
    @Autowired
    private ExcelPoligonReader excelPoligonReader;
    @Autowired
    private CalculationDataService calculationDataService;
    @Autowired
    private PoligonDataService poligonDataService;
    @Autowired
    private ConsistentApproximationMethod consistentApproximationMethod;
    @Autowired
    private PoligonMethod poligonMethod;

    @PostMapping("/result-1")
    private String calculation1(Principal principal, Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file) {
        CalculationData res = consistentApproximationMethod.calculate(excelReader.getCalculationData(file).setCalculationTypeName(type).setDate(Timestamp.valueOf(LocalDateTime.now())));
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        if (ofNullable(principal).isPresent())
            if (ofNullable(principal.getName()).isPresent()) {
                res = calculationDataService.save(res.setName(file.getOriginalFilename().replace(".xls", "")).setUserName(principal.getName()));
                model.addAttribute("idRes", res.getId());
            }
        model.addAttribute("calcs", pointDtoParser.parse(res));
        model.addAttribute("length", res.getApproximationMoveList().get(0).getApproximations().size());
        return "result";
    }

    @PostMapping("/v2/result-1")
    private String calculation1(Principal principal, Model model, @RequestBody CalculationData calculationData) {
        CalculationData res = consistentApproximationMethod.calculate(calculationData.setDate(Timestamp.valueOf(LocalDateTime.now())));
        model.addAttribute("classSystem", calculationData.getCalculationTypeName().name());
        model.addAttribute("type",  calculationData.getCalculationTypeName().name());
        model.addAttribute("file", "file.getOriginalFilename()");
        if (ofNullable(principal).isPresent())
            if (ofNullable(principal.getName()).isPresent()) {
                res = calculationDataService.save(res.setUserName(principal.getName()));
                model.addAttribute("idRes", res.getId());
            }
        model.addAttribute("calcs", pointDtoParser.parse(res));
        model.addAttribute("length", res.getApproximationMoveList().get(0).getApproximations().size());
        return "result";
    }

    @GetMapping("/download/approximation/{id}")
    private ModelAndView download1(@PathVariable Long id) {
        CalculationData calculationData = calculationDataService.findOne(id);
        List<PointDto> dtoList = pointDtoParser.parse(calculationData);
        Map<String, Object> map = new HashMap<>();
        map.put("name", calculationData.getName());
        map.put("pointDto", dtoList);
        map.put("approximationDtoLength", (long) calculationData.getApproximationMoveList().get(0).getApproximations().size());
        return new ModelAndView(new ExcelViewReport(), map);
    }

    @GetMapping("/download/poligon/{id}")
    private ModelAndView downloadPoligon(@PathVariable Long id) {
        PoligonData poligonData = poligonDataService.findOne(id);
//        List<PointDto> dtoList = pointDtoParser.parse(calculationData);
        Map<String, Object> map = new HashMap<>();
        map.put("name", poligonData.getName());
        map.put("pointDto", poligonData.getReperList());
//        map.put("approximationDtoLength", (long) calculationData.getApproximationMoveList().get(0).getApproximations().size());
        return new ModelAndView(new ExcelViewReport(), map);
    }

    @PostMapping("/result-2")
    private String calculation2(Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file,Principal principal) {
        PoligonData res = poligonMethod.calculate(excelPoligonReader.getPoligonData(file).setCalculationTypeName(type).setDate(Timestamp.valueOf(LocalDateTime.now())));
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        model.addAttribute("pointDto", res.getReperList());
//        System.err.println(res);
        if (ofNullable(principal).isPresent())
            if (ofNullable(principal.getName()).isPresent()) {
                res = poligonDataService.save(res.setName(file.getOriginalFilename().replace(".xls", "")).setUserName(principal.getName()));
                model.addAttribute("idRes", res.getId());
            }
//        model.addAttribute("calcs", pointDtoParser.parse(res));
//        model.addAttribute("length", res.getApproximationMoveList().get(0).getApproximations().size());
        return "result-poligon";
    }

    @PostMapping("/v2/result-2")
    private String calculation2(Model model, @RequestBody PoligonData poligonData,Principal principal) {
        PoligonData res = poligonMethod.calculate(poligonData.setDate(Timestamp.valueOf(LocalDateTime.now())));
        model.addAttribute("classSystem", poligonData.getCalculationTypeName().name());
        model.addAttribute("type", poligonData.getCalculationTypeName().name());
        model.addAttribute("file", "file.getOriginalFilename()");
        model.addAttribute("pointDto", res.getReperList());
//        System.err.println(res);
        if (ofNullable(principal).isPresent())
            if (ofNullable(principal.getName()).isPresent()) {
                res = poligonDataService.save(res.setUserName(principal.getName()));
                model.addAttribute("idRes", res.getId());
            }
//        model.addAttribute("calcs", pointDtoParser.parse(res));
//        model.addAttribute("length", res.getApproximationMoveList().get(0).getApproximations().size());
        return "result-poligon";
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
