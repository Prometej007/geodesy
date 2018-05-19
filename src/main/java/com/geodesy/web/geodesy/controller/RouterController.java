package com.geodesy.web.geodesy.controller;

import com.geodesy.web.geodesy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by danul on 15.06.2017.
 */
@Controller
public class RouterController {
    @Autowired
    private TestService testService;

    @RequestMapping({"/", "/home", "/home/"})
    public String index(Model model) {
//        model.addAttribute("name", testService.test("test"));
        return "home";
    }

    @RequestMapping({"/level-the-system"})
    public String levelTheSystem(Model model) {
//        model.addAttribute("name", testService.test("test"));
        return "level-the-system";
    }


    @RequestMapping({"/result"})
    public String result(Model model) {
//        model.addAttribute("name", testService.test("test"));
        return "result";
    }


}
