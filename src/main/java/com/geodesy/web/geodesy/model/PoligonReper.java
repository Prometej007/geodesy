package com.geodesy.web.geodesy.model;

public class PoligonReper {
    private Long id;
    private String name;
    private Double height;

    public Long getId() {
        return id;
    }

    public PoligonReper setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PoligonReper setName(String name) {
        this.name = name;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public PoligonReper setHeight(Double height) {
        this.height = height;
        return this;
    }
}
