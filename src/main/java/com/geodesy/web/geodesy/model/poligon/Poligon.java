package com.geodesy.web.geodesy.model.poligon;

import com.geodesy.web.geodesy.model.base.GObject;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Poligon extends GObject {
    private Double perimeter;
    private Double mischief;
    @OneToMany(mappedBy = "poligon", cascade = CascadeType.REFRESH)
    private List<PoligonMove> poligonMoves;
    @ManyToOne
    private PoligonData data;

    public PoligonData getData() {
        return data;
    }

    public Poligon setData(PoligonData data) {
        this.data = data;
        return this;
    }

    @Override
    public Poligon setId(Long id) {
        return (Poligon) super.setId(id);
    }

    @Override
    public Poligon setName(String name) {
        return (Poligon) super.setName(name);
    }

    public List<PoligonMove> getPoligonMoves() {
        return poligonMoves;
    }

    public Poligon setPoligonMoves(List<PoligonMove> poligonMoves) {
        this.poligonMoves = poligonMoves;
        return this;
    }

    public Poligon addPoligonMove(PoligonMove poligonMove) {
        if (poligonMoves == null)
            poligonMoves = new ArrayList<>();
        poligonMoves.add(poligonMove);
        return this;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public Poligon setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
        return this;
    }

    public Double getMischief() {
        return mischief;
    }

    public Poligon setMischief(Double mischief) {
        this.mischief = mischief;
        return this;
    }

    @Override
    public String toString() {
        return "Poligon{\n" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tperimeter=" + (perimeter == null ? "null" : DoubleFormatter.format(perimeter)) +
                ", \n\tmischief=" + (mischief == null ? "null" : DoubleFormatter.format(mischief)) +
                ", \n\tpoligonMoves=" + poligonMoves +
                "\n}";
    }
}
