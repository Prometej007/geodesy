package com.geodesy.web.geodesy.controller;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.service.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @Autowired
    ExcelReader excelReader;

    @RequestMapping(method =RequestMethod.POST, value="/test-file")
    private ResponseEntity<CalculationData> stringRequestEntity(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<CalculationData>(excelReader.getCalculationData(file),HttpStatus.OK);
    }


}
