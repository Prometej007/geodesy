package com.geodesy.web.geodesy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by danul on 15.06.2017.
 */
@Controller
public class RouterController {
    @RequestMapping({"/", "/main", "/home"})
    public String index(Model model) {
        model.addAttribute("name", "test");
        return "index";
    }
}
