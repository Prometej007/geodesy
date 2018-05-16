package com.geodesy.web.geodesy.model;

import com.geodesy.web.geodesy.model.enums.CalculationType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class CalculationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "calculationData")
    private List<Reper> reperList;
    @OneToMany(mappedBy = "calculationData")
    private List<Move> moveList;
    private CalculationType calculationType;
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


    public CalculationType getCalculationType() {
        return calculationType;
    }

    public CalculationData setCalculationType(CalculationType calculationType) {
        this.calculationType = calculationType;
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
                ", \ncalculationType=" + calculationType +
                ", \nniu=" + niu +
                ", \nm=" + m +
                '}';
    }
}
