package com.geodesy.web.geodesy.model;

public class PoligonMove {
    private Long id;
    private String nmae;
    private Integer stationCount;
    private Double distance;
    private Double exceeding;
    private Double weight;
    private Double correction;

    public Long getId() {
        return id;
    }

    public PoligonMove setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNmae() {
        return nmae;
    }

    public PoligonMove setNmae(String nmae) {
        this.nmae = nmae;
        return this;
    }

    public Integer getStationCount() {
        return stationCount;
    }

    public PoligonMove setStationCount(Integer stationCount) {
        this.stationCount = stationCount;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public PoligonMove setDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public PoligonMove setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getExceeding() {
        return exceeding;
    }

    public PoligonMove setExceeding(Double exceeding) {
        this.exceeding = exceeding;
        return this;
    }

    public Double getCorrection() {
        return correction;
    }

    public PoligonMove setCorrection(Double correction) {
        this.correction = correction;
        return this;
    }
}
