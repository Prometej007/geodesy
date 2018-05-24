package com.geodesy.web.geodesy.model.poligon;

import com.geodesy.web.geodesy.model.base.Data;
import com.geodesy.web.geodesy.model.base.Reper;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PoligonData extends Data {
    private List<Poligon> poligonList;

    public List<Poligon> getPoligonList() {
        return poligonList;
    }

    public PoligonData setPoligonList(List<Poligon> poligonList) {
        this.poligonList = poligonList;
        return this;
    }

    public PoligonData addPoligon(Poligon poligon) {
        if (poligonList == null)
            poligonList = new ArrayList<>();
        poligonList.add(poligon);
        return this;
    }

    @Override
    public List<PoligonReper> getReperList() {
        return super.getReperList().stream().map(reper -> (PoligonReper) reper).collect(Collectors.toList());
    }

    @Override
    public PoligonData setReperList(List<? extends Reper> reperList) {
        return (PoligonData) super.setReperList(reperList);
    }

    @Override
    public PoligonData setDate(Timestamp date) {
        return (PoligonData) super.setDate(date);
    }

    @Override
    public PoligonData setCalculationTypeName(CalculationTypeName calculationTypeName) {
        return (PoligonData) super.setCalculationTypeName(calculationTypeName);
    }

    @Override
    public PoligonData setId(Long id) {
        return (PoligonData) super.setId(id);
    }

    @Override
    public PoligonData setName(String name) {
        return (PoligonData) super.setName(name);
    }

    @Override
    public String toString() {
        return "PoligonData{" +
                "\n\tpoligonList=" + poligonList +
                ",\n\t reperList=" + reperList +
                ",\n\t date=" + date +
                ",\n\t calculationTypeName=" + calculationTypeName +
                ",\n\t id=" + id +
                ",\n\t name='" + name + '\'' +
                "\n}";
    }
}
