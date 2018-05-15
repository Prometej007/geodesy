package com.geodesy.web.geodesy.model;

import com.geodesy.web.geodesy.model.enums.MoveType;

import javax.persistence.*;
import java.util.List;

//@Entity
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private MoveType moveType;
    // h
    private Double difference;
    // n
    private Integer stationCount;
    // L
    private Double distance;
    // P
    private Double weight;
    // P`
    private Double weightStroke;
    // (H) I, II, III, IV, V ...
    private List<Double> approximations;
    // v
    private Double correction;
    @ManyToOne
    private CalculationData calculationData;

    public Long getId() {
        return id;
    }

    public Move setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Move setName(String name) {
        this.name = name;
        return this;
    }

    public Double getDifference() {
        return difference;
    }

    public Move setDifference(Double difference) {
        this.difference = difference;
        return this;
    }

    public Integer getStationCount() {
        return stationCount;
    }

    public Move setStationCount(Integer stationCount) {
        this.stationCount = stationCount;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Move setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public Move setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getWeightStroke() {
        return weightStroke;
    }

    public Move setWeightStroke(Double weightStroke) {
        this.weightStroke = weightStroke;
        return this;
    }

    public List<Double> getApproximations() {
        return approximations;
    }

    public Move setApproximations(List<Double> approximations) {
        this.approximations = approximations;
        return this;
    }

    public Double getCorrection() {
        return correction;
    }

    public Move setCorrection(Double correction) {
        this.correction = correction;
        return this;
    }

    public CalculationData getCalculationData() {
        return calculationData;
    }

    public Move setCalculationData(CalculationData calculationData) {
        this.calculationData = calculationData;
        return this;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public Move setMoveType(MoveType moveType) {
        this.moveType = moveType;
        return this;
    }
}
