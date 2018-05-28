package com.geodesy.web.geodesy.model.approximation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geodesy.web.geodesy.model.base.Reper;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;
import com.geodesy.web.geodesy.model.utils.enums.ReperType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class ApproximationReper extends Reper {
    @JsonIgnore
    @ManyToOne
    private CalculationData data;
    @Enumerated
    private ReperType reperType;

    public ApproximationReper setId(Long id) {
        return (ApproximationReper) super.setId(id);
    }

    public ApproximationReper setName(String name) {
        return (ApproximationReper) super.setName(name);
    }

    public ApproximationReper setHeight(Double height) {
        return (ApproximationReper) super.setHeight(height);
    }

    public CalculationData getData() {
        return data;
    }

    public ApproximationReper setData(CalculationData calculationData) {
        this.data = calculationData;
        return this;
    }

    public ReperType getReperType() {
        return reperType;
    }

    public ApproximationReper setReperType(ReperType reperType) {
        this.reperType = reperType;
        return this;
    }

    @Override
    public String toString() {
        return "ApproximationReper{\n" +
                "id=" + id +
                ", \nname='" + (name == null ? "null" : name) + '\'' +
                ", \nheight=" + (height == null ? "null" : DoubleFormatter.format(height)) +
                ", \nreperType=" + (reperType == null ? "null" : reperType) +
                '}';
    }
}
