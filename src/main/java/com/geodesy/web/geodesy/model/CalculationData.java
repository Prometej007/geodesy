package com.geodesy.web.geodesy.model;

import javax.persistence.*;
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

    public List<Move> getMoveList() {
        return moveList;
    }

    public CalculationData setMoveList(List<Move> moveList) {
        this.moveList = moveList;
        return this;
    }
}
