package com.geodesy.web.geodesy.model;

import javax.persistence.*;

@Entity
public class Approximation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer step;
    private Double value;
    @ManyToOne
    private Move move;

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

    public Move getMove() {
        return move;
    }

    public Approximation setMove(Move move) {
        this.move = move;
        return this;
    }

    public Integer getStep() {
        return step;
    }

    public Approximation setStep(Integer step) {
        this.step = step;
        return this;
    }
}
