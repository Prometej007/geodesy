package com.geodesy.web.geodesy.dto;

import java.util.ArrayList;
import java.util.List;

public class PointDto {
    // номер нульової точки
    private Long number;
    // порядково з таблиці
    private List<PointOneDto> pointOne;
    private List<String> checkParams;

    public Long getNumber() {
        return number;
    }

    public PointDto setNumber(Long number) {
        this.number = number;
        return this;
    }

    public List<PointOneDto> getPointOne() {
        return pointOne;
    }

    public PointDto setPointOne(List<PointOneDto> pointOne) {
        this.pointOne = pointOne;
        return this;
    }

    public PointDto addPointOne(PointOneDto pointOneDto){
        if(this.pointOne==null)
            this.pointOne=new ArrayList<>();
        this.pointOne.add(pointOneDto);
        return this;
    }

    public List<String> getCheckParams() {
        return checkParams;
    }

    public PointDto setCheckParams(List<String> checkParams) {
        this.checkParams = checkParams;
        return this;
    }
}
