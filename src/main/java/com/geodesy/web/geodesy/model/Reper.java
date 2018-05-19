package com.geodesy.web.geodesy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geodesy.web.geodesy.model.enums.ReperType;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import javax.persistence.*;

@Entity
public class Reper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double height;
    @JsonIgnore
    @ManyToOne
    private CalculationData calculationData;
    @Enumerated
    private ReperType reperType;

    public Long getId() {
        return id;
    }

    public Reper setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Reper setName(String name) {
        this.name = name;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public Reper setHeight(Double height) {
        this.height = height;
        return this;
    }

    public CalculationData getCalculationData() {
        return calculationData;
    }

    public Reper setCalculationData(CalculationData calculationData) {
        this.calculationData = calculationData;
        return this;
    }

    public ReperType getReperType() {
        return reperType;
    }

    public Reper setReperType(ReperType reperType) {
        this.reperType = reperType;
        return this;
    }

    @Override
    public String toString() {
        return "Reper{\n" +
                "id=" + id +
                ", \nname='" + (name == null ? "null" : name) + '\'' +
                ", \nheight=" + (height == null ? "null" : DoubleFormatter.format(height)) +
                ", \nreperType=" + (reperType == null ? "null" : reperType) +
                '}';
    }
}
