package com.geodesy.web.geodesy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geodesy.web.geodesy.model.enums.MoveType;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated
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
    @OneToMany(mappedBy = "move")
    private List<Approximation> approximations;
    // v
    private Double correction;
    // P`v
    private Double weightStrokeCorrection;
    // P`vv
    private Double weightStrokeCorrectionCorrection;
    @JsonIgnore
    @ManyToOne
    private CalculationData calculationData;

    public Move() {
        distance = .0;
        stationCount = 0;
    }

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

    public List<Approximation> getApproximations() {
        if (approximations == null)
            approximations = new ArrayList<>();
        return approximations;
    }

    public Move setApproximations(List<Approximation> approximations) {
        this.approximations = approximations;
        return this;
    }

    public Move addApproximation(Approximation approximation) {
        if (this.approximations == null)
            this.approximations = new ArrayList<>();
        this.approximations.add(approximation);
        return this;
    }

    public Double getWeightStrokeCorrection() {
        return weightStrokeCorrection;
    }

    public Move setWeightStrokeCorrection(Double weightStrokeCorrection) {
        this.weightStrokeCorrection = weightStrokeCorrection;
        return this;
    }

    public Double getWeightStrokeCorrectionCorrection() {
        return weightStrokeCorrectionCorrection;
    }

    public Move setWeightStrokeCorrectionCorrection(Double weightStrokeCorrectionCorrection) {
        this.weightStrokeCorrectionCorrection = weightStrokeCorrectionCorrection;
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

    @Override
    public String toString() {
        return "Move{\n" +
                "id=" + id +
                ", \nname='" + (name == null ? "null" : name) + '\'' +
                ", \nmoveType=" + (moveType == null ? "null" : moveType) +
                ", \ndifference=" + (difference == null ? "null" : DoubleFormatter.format(difference)) +
                ", \nstationCount=" + (stationCount == null ? "null" : stationCount) +
                ", \ndistance=" + (distance == null ? "null" : DoubleFormatter.format(distance)) +
                ", \nweight=" + (weight == null ? "null" : DoubleFormatter.format(weight)) +
                ", \nweightStroke=" + (weightStroke == null ? "null" : DoubleFormatter.format(weightStroke)) +
                ", \napproximations=" + (approximations == null ? "null" : approximations) +
                ", \ncorrection=" + (correction == null ? "null" : DoubleFormatter.format(correction)) +
                ", \nweightStrokeCorrection=" + (weightStrokeCorrection == null ? "null" : DoubleFormatter.format(weightStrokeCorrection)) +
                ", \nweightStrokeCorrectionCorrection=" + (weightStrokeCorrectionCorrection == null ? "null" : DoubleFormatter.format(weightStrokeCorrectionCorrection)) +
                '}';
    }
}
