package com.geodesy.web.geodesy.model.approximation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.geodesy.web.geodesy.model.base.Data;
import com.geodesy.web.geodesy.model.utils.DateDeserializer;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CalculationData extends Data {
    @OneToMany(mappedBy = "calculationData", cascade = CascadeType.REFRESH)
    private List<ApproximationMove> approximationMoveList;
    private Double niu;
    private Double m;
    @OneToMany(mappedBy = "data", cascade = CascadeType.REFRESH)
    private List<ApproximationReper> reperList;

    public List<ApproximationMove> getApproximationMoveList() {
        return approximationMoveList;
    }

    public CalculationData setApproximationMoveList(List<ApproximationMove> approximationMoveList) {
        this.approximationMoveList = approximationMoveList;
        return this;
    }

    public CalculationData addMove(ApproximationMove approximationMove) {
        if (approximationMoveList == null)
            approximationMoveList = new ArrayList<>();
        this.approximationMoveList.add(approximationMove);
        return this;
    }

    public Double getNiu() {
        return niu;
    }

    public CalculationData setNiu(Double niu) {
        this.niu = niu;
        return this;
    }

    public Double getM() {
        return m;
    }

    public CalculationData setM(Double m) {
        this.m = m;
        return this;
    }

    public List<ApproximationReper> getReperList() {
        return reperList;
    }

    public CalculationData setReperList(List<ApproximationReper> reperList) {
        this.reperList = reperList;
        return this;
    }

    @Override
    public CalculationData setCalculationTypeName(CalculationTypeName calculationTypeName) {
        return (CalculationData) super.setCalculationTypeName(calculationTypeName);
    }

    @Override
    public CalculationData setId(Long id) {
        return (CalculationData) super.setId(id);
    }

    @Override
    public CalculationData setName(String name) {
        return (CalculationData) super.setName(name);
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public CalculationData setDate(Timestamp date) {
        return (CalculationData) super.setDate(date);
    }

    @Override
    public String toString() {
        return "CalculationData{\n" +
                "id=" + id +
                ", \napproximationReperList=" + reperList +
                ", \napproximationMoveList=" + approximationMoveList +
                ", \ncalculationTypeName=" + calculationTypeName +
                ", \nniu=" + niu +
                ", \nm=" + m +
                '}';
    }
}
