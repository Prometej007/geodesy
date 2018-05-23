package com.geodesy.web.geodesy.model.base;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Move extends GObject {
    protected Integer stationCount;
    protected Double distance;
    protected Double difference;
    protected Double weight;
    protected Double correction;

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

    public Double getDifference() {
        return difference;
    }

    public Move setDifference(Double difference) {
        this.difference = difference;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public Move setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getCorrection() {
        return correction;
    }

    public Move setCorrection(Double correction) {
        this.correction = correction;
        return this;
    }

    @Override
    public Move setId(Long id) {
        return (Move) super.setId(id);
    }

    @Override
    public Move setName(String name) {
        return (Move) super.setName(name);
    }
}
