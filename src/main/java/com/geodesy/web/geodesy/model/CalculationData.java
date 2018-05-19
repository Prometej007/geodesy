package com.geodesy.web.geodesy.model;

import com.geodesy.web.geodesy.model.enums.CalculationTypeName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CalculationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "calculationData",cascade = CascadeType.ALL)
    private List<Reper> reperList;
    @OneToMany(mappedBy = "calculationData",cascade = CascadeType.ALL)
    private List<Move> moveList;
    private CalculationTypeName calculationTypeName;
    private Double niu;
    private Double m;

    public Long getId() {
        return id;
    }

    public CalculationData setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Reper> getReperList() {
        return reperList;
    }

    public CalculationData setReperList(List<Reper> reperList) {
        this.reperList = reperList;
        return this;
    }

    public CalculationData addReper(Reper reper) {
        if (reperList == null)
            reperList = new ArrayList<>();
        this.reperList.add(reper);
        return this;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public CalculationData setMoveList(List<Move> moveList) {
        this.moveList = moveList;
        return this;
    }

    public CalculationData addMove(Move move) {
        if (moveList == null)
            moveList = new ArrayList<>();
        this.moveList.add(move);
        return this;
    }


    public CalculationTypeName getCalculationTypeName() {
        return calculationTypeName;
    }

    public CalculationData setCalculationTypeName(CalculationTypeName calculationTypeName) {
        this.calculationTypeName = calculationTypeName;
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

    @Override
    public String toString() {
        return "CalculationData{\n" +
                "id=" + id +
                ", \nreperList=" + reperList +
                ", \nmoveList=" + moveList +
                ", \ncalculationTypeName=" + calculationTypeName +
                ", \nniu=" + niu +
                ", \nm=" + m +
                '}';
    }
}
