package com.geodesy.web.geodesy.model.poligon;

import com.geodesy.web.geodesy.model.base.Move;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;

import java.util.Objects;

public class PoligonMove extends Move {
    @Override
    public PoligonMove setStationCount(Integer stationCount) {
        return (PoligonMove) super.setStationCount(stationCount);
    }

    @Override
    public PoligonMove setDistance(Double distance) {
        return (PoligonMove) super.setDistance(distance);
    }

    @Override
    public PoligonMove setDifference(Double difference) {
        return (PoligonMove) super.setDifference(difference);
    }

    @Override
    public PoligonMove setWeight(Double weight) {
        return (PoligonMove) super.setWeight(weight);
    }

    @Override
    public PoligonMove setCorrection(Double correction) {
        return (PoligonMove) super.setCorrection(correction);
    }

    @Override
    public PoligonMove setId(Long id) {
        return (PoligonMove) super.setId(id);
    }

    @Override
    public PoligonMove setName(String name) {
        return (PoligonMove) super.setName(name);
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
                ", \n\tweight=" + (weight == null ? "null" : DoubleFormatter.format(weight)) +
                ", \n\tcorrection=" + (correction == null ? "null" : DoubleFormatter.format(correction)) +
                "\n}";
    }
}
