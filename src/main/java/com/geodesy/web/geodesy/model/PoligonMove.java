package com.geodesy.web.geodesy.model;

import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import java.util.Objects;

public class PoligonMove {
    private Long id;
    private String name;
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

    public String getName() {
        return name;
    }

    public PoligonMove setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoligonMove that = (PoligonMove) o;
        String rp1 = this.getName().split("-")[0];
        String rp2 = this.getName().split("-")[1];
//        System.err.println(String.format("that.getName(%s)==%s-%s (%s)",that.getName(),rp2,rp1,Objects.equals(that.getName(), rp2 + "-" + rp1)));
        return Objects.equals(that.getName(), rp2 + "-" + rp1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PoligonMove{\n" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tstationCount=" + stationCount +
                ", \n\tdistance=" + (distance == null ? "null" : DoubleFormatter.format(distance)) +
                ", \n\texceeding=" + (exceeding == null ? "null" : DoubleFormatter.format(exceeding)) +
                ", \n\tweight=" + (weight == null ? "null" : DoubleFormatter.format(weight)) +
                ", \n\tcorrection=" + (correction == null ? "null" : DoubleFormatter.format(correction)) +
                "\n}";
    }
}
