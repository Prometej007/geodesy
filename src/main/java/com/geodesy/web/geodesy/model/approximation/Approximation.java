package com.geodesy.web.geodesy.model.approximation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import javax.persistence.*;

@Entity
public class Approximation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer step;
    private Double value;
    @JsonIgnore
    @ManyToOne
    private ApproximationMove approximationMove;

    public Long getId() {
        return id;
    }

    public Approximation setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public Approximation setValue(Double value) {
        this.value = value;
        return this;
    }

    public ApproximationMove getApproximationMove() {
        return approximationMove;
    }

    public Approximation setApproximationMove(ApproximationMove approximationMove) {
        this.approximationMove = approximationMove;
        return this;
    }

    public Integer getStep() {
        return step;
    }

    public Approximation setStep(Integer step) {
        this.step = step;
        return this;
    }

    @Override
    public String toString() {
        return "Approximation{" +
                "id=" + id +
                ", step=" + step +
                ", value=" + DoubleFormatter.format(value) +
                '}';
    }
}
