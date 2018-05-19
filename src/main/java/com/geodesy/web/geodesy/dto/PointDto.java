package com.geodesy.web.geodesy.dto;

import java.util.List;

public class PointDto {
    // номер нульової точки
    private Long number;
    // порядково з таблиці
    private List<PointOneDto> pointOne;

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
}
