package com.geodesy.web.geodesy.model;

import java.util.List;

public class Poligon {
    private Long id;
    private String name;
    private Double perimeter;
    private Double mischief;
    private List<PoligonMove> poligonMoves;

    public Long getId() {
        return id;
    }

    public Poligon setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Poligon setName(String name) {
        this.name = name;
        return this;
    }

    public List<PoligonMove> getPoligonMoves() {
        return poligonMoves;
    }

    public Poligon setPoligonMoves(List<PoligonMove> poligonMoves) {
        this.poligonMoves = poligonMoves;
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
}
