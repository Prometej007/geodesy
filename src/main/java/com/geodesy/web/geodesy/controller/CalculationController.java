package com.geodesy.web.geodesy.controller;

import com.geodesy.web.geodesy.builder.ExcelViewReport;
import com.geodesy.web.geodesy.model.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.enums.ClassSystem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/calculation")
public class CalculationController {


    @PostMapping("/result-1")
    private String calculation1(Model model, @RequestParam ClassSystem classSystem, @RequestParam CalculationTypeName type, @RequestParam MultipartFile file) {
        model.addAttribute("classSystem", classSystem.name());
        model.addAttribute("type", type.name());
        model.addAttribute("file", file.getOriginalFilename());
        model.addAttribute("calc", file.getOriginalFilename());
        return "result";
    }

    @GetMapping("/download/file")
    private ModelAndView download1(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","test");
        map.put("pointDto","pointDto");
        map.put("approximationDtoLength","approximationDtoLength");
        return new ModelAndView(new ExcelViewReport(),map);
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
