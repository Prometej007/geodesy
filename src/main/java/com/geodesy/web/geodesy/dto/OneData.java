package com.geodesy.web.geodesy.dto;

import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;

import java.util.List;

public class OneData {
    private Long idData;
    private List<PointDto> pointDtos;
    private CalculationTypeName calculationTypeName;

    public Long getIdData() {
        return idData;
    }

    public OneData setIdData(Long idData) {
        this.idData = idData;
        return this;
    }

    public List<PointDto> getPointDtos() {
        return pointDtos;
    }

    public OneData setPointDtos(List<PointDto> pointDtos) {
        this.pointDtos = pointDtos;
        return this;
    }

    public CalculationTypeName getCalculationTypeName() {
        return calculationTypeName;
    }

    public OneData setCalculationTypeName(CalculationTypeName calculationTypeName) {
        this.calculationTypeName = calculationTypeName;
        return this;
    }
}
