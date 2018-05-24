package com.geodesy.web.geodesy.model.approximation;

import com.geodesy.web.geodesy.model.base.Move;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;
import com.geodesy.web.geodesy.model.utils.enums.MoveType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ApproximationMove extends Move {
    @Enumerated
    private MoveType moveType;
    // P`
    private Double weightStroke;
    // (H) I, II, III, IV, V ...
    @OneToMany(mappedBy = "move")
    private List<Approximation> approximations;
    // P`v
    private Double weightStrokeCorrection;
    // P`vv
    private Double weightStrokeCorrectionCorrection;

    @ManyToOne
    private CalculationData calculationData;

    public ApproximationMove() {
        distance = .0;
        stationCount = 0;
    }

    public Double getWeightStroke() {
        return weightStroke;
    }

    public ApproximationMove setWeightStroke(Double weightStroke) {
        this.weightStroke = weightStroke;
        return this;
    }

    public List<Approximation> getApproximations() {
        if (approximations == null)
            approximations = new ArrayList<>();
        return approximations;
    }

    public ApproximationMove setApproximations(List<Approximation> approximations) {
        this.approximations = approximations;
        return this;
    }

    public ApproximationMove addApproximation(Approximation approximation) {
        if (this.approximations == null)
            this.approximations = new ArrayList<>();
        this.approximations.add(approximation);
        return this;
    }

    public Double getWeightStrokeCorrection() {
        return weightStrokeCorrection;
    }

    public ApproximationMove setWeightStrokeCorrection(Double weightStrokeCorrection) {
        this.weightStrokeCorrection = weightStrokeCorrection;
        return this;
    }

    public Double getWeightStrokeCorrectionCorrection() {
        return weightStrokeCorrectionCorrection;
    }

    public ApproximationMove setWeightStrokeCorrectionCorrection(Double weightStrokeCorrectionCorrection) {
        this.weightStrokeCorrectionCorrection = weightStrokeCorrectionCorrection;
        return this;
    }

    public CalculationData getCalculationData() {
        return calculationData;
    }

    public ApproximationMove setCalculationData(CalculationData calculationData) {
        this.calculationData = calculationData;
        return this;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public ApproximationMove setMoveType(MoveType moveType) {
        this.moveType = moveType;
        return this;
    }

    @Override
    public ApproximationMove setStationCount(Integer stationCount) {
        return (ApproximationMove)super.setStationCount(stationCount);
    }

    @Override
    public ApproximationMove setDistance(Double distance) {
        return (ApproximationMove)super.setDistance(distance);
    }

    @Override
    public ApproximationMove setDifference(Double difference) {
        return (ApproximationMove)super.setDifference(difference);
    }

    @Override
    public ApproximationMove setWeight(Double weight) {
        return (ApproximationMove)super.setWeight(weight);
    }

    @Override
    public ApproximationMove setCorrection(Double correction) {
        return (ApproximationMove)super.setCorrection(correction);
    }

    @Override
    public ApproximationMove setId(Long id) {
        return (ApproximationMove)super.setId(id);
    }

    @Override
    public ApproximationMove setName(String name) {
        return (ApproximationMove)super.setName(name);
    }

    @Override
    public String toString() {
        return "ApproximationMove{\n" +
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
