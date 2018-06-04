package com.geodesy.web.geodesy.controller;

import com.geodesy.web.geodesy.dto.OneData;
import com.geodesy.web.geodesy.dto.utils.PointDtoParser;
import com.geodesy.web.geodesy.repository.CalculationDataRepository;
import com.geodesy.web.geodesy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by danul on 15.06.2017.
 */
@Controller
public class RouterController {
    private final PointDtoParser pointDtoParser = new PointDtoParser();
    @Autowired
    private TestService testService;
    @Autowired
    private CalculationDataRepository calculationDataRepository;

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

    @RequestMapping({"/my-results"})
    public String myResults(Principal principal, Model model) {

        if (ofNullable(principal).isPresent()) {
            if (ofNullable(principal.getName()).isPresent()) {
                model.addAttribute("calcss", calculationDataRepository.findAllByUserName(principal.getName()).stream()
                        .map(calculationData -> new OneData().setPointDtos(pointDtoParser.parse(calculationData)).setCalculationTypeName(calculationData.getCalculationTypeName()).setIdData(calculationData.getId())).collect(toList()));
                return "my-results";
            }
        }
        return "redirect:/sign-in";

    }

    @RequestMapping({"/about-us"})
    public String aboutUs() {
        return "about-us";
    }

    @RequestMapping({"/contacts"})
    public String contacts() {
        return "contacts";
    }

    @RequestMapping({"/sign-in"})
    public String signIn() {
        return "sign-in";
    }

    @RequestMapping({"/sign-up"})
    public String signUp() {
        return "sign-up";
    }


}
