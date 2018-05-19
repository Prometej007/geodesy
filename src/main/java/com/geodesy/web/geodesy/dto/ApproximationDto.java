package com.geodesy.web.geodesy.dto;

public class ApproximationDto {

    private String value;

    public String getValue() {
        return value;
    }

    public ApproximationDto setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "ApproximationDto{" +
                "value='" + value + '\'' +
                '}';
    }
}
