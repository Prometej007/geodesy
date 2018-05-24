package com.geodesy.web.geodesy.model.poligon;

import com.geodesy.web.geodesy.model.base.Reper;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import javax.persistence.ManyToOne;

public class PoligonReper extends Reper {
    @ManyToOne
    protected PoligonData data;

    public PoligonData getData() {
        return data;
    }

    public PoligonReper setData(PoligonData data) {
        this.data = data;
        return this;
    }

    @Override
    public PoligonReper setHeight(Double height) {
        return (PoligonReper) super.setHeight(height);
    }

    @Override
    public PoligonReper setId(Long id) {
        return (PoligonReper) super.setId(id);
    }

    @Override
    public PoligonReper setName(String name) {
        return (PoligonReper) super.setName(name);
    }

    @Override
    public String toString() {
        return "PoligonReper{\n" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\theight=" + (height == null ? "null" : DoubleFormatter.format(height)) +
                "\n}";
    }
}
