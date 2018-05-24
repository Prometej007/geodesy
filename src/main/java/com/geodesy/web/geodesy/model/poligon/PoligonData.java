package com.geodesy.web.geodesy.model.poligon;

import com.geodesy.web.geodesy.model.base.Data;
import com.geodesy.web.geodesy.model.base.Reper;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;

import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PoligonData extends Data {
    private List<Poligon> poligonList;
    @OneToMany(mappedBy = "data")
    protected List<PoligonReper> reperList;

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

    public List<PoligonReper> getReperList() {
        return reperList;
    }

    public PoligonData setReperList(List<PoligonReper> reperList) {
        this.reperList = reperList;
        return this;
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
