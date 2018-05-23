package com.geodesy.web.geodesy.model;

import com.geodesy.web.geodesy.model.enums.CalculationTypeName;

import java.util.List;

public class PoligonData {
    private Long id;
    private PoligonReper poligonReper;
    private List<Poligon> poligonList;
    private CalculationTypeName calculationTypeName;

    public Long getId() {
        return id;
    }

    public PoligonData setId(Long id) {
        this.id = id;
        return this;
    }

    public PoligonReper getPoligonReper() {
        return poligonReper;
    }

    public PoligonData setPoligonReper(PoligonReper poligonReper) {
        this.poligonReper = poligonReper;
        return this;
    }

    public List<Poligon> getPoligonList() {
        return poligonList;
    }

    public PoligonData setPoligonList(List<Poligon> poligonList) {
        this.poligonList = poligonList;
        return this;
    }

    public CalculationTypeName getCalculationTypeName() {
        return calculationTypeName;
    }

    public PoligonData setCalculationTypeName(CalculationTypeName calculationTypeName) {
        this.calculationTypeName = calculationTypeName;
        return this;
    }
}
