package com.geodesy.web.geodesy.dto;

import java.util.List;

public class PointOneDto {
    // ходи які сходяться у вузловій точці
    private String nameMuve;
    //Назви вузлових Реперів
    private String nameReper;
    //    висота вузлових реперів
    private String height;
    //    сумми перевищень п оходах
    private String sum;
    //    кількість штативів
    private String station;
    //    pji
    private String weight;
    //    p`ji
    private String _weight;
    // наближення
    private List<ApproximationDto> approximationDto;
    //    VMM
    private String correction;
    //    p`VMM
    private String weightCorrection;
    //    p`vvmm2
    private String weightCorrectionCorrection;

    public String getNameMuve() {
        return nameMuve;
    }

    public PointOneDto setNameMuve(String nameMuve) {
        this.nameMuve = nameMuve;
        return this;
    }

    public String getNameReper() {
        return nameReper;
    }

    public PointOneDto setNameReper(String nameReper) {
        this.nameReper = nameReper;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public PointOneDto setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getSum() {
        return sum;
    }

    public PointOneDto setSum(String sum) {
        this.sum = sum;
        return this;
    }

    public String getStation() {
        return station;
    }

    public PointOneDto setStation(String station) {
        this.station = station;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public PointOneDto setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String get_weight() {
        return _weight;
    }

    public PointOneDto set_weight(String _weight) {
        this._weight = _weight;
        return this;
    }

    public List<ApproximationDto> getApproximationDto() {
        return approximationDto;
    }

    public PointOneDto setApproximationDto(List<ApproximationDto> approximationDto) {
        this.approximationDto = approximationDto;
        return this;
    }

    public String getCorrection() {
        return correction;
    }

    public PointOneDto setCorrection(String correction) {
        this.correction = correction;
        return this;
    }

    public String getWeightCorrection() {
        return weightCorrection;
    }

    public PointOneDto setWeightCorrection(String weightCorrection) {
        this.weightCorrection = weightCorrection;
        return this;
    }

    public String getWeightCorrectionCorrection() {
        return weightCorrectionCorrection;
    }

    public PointOneDto setWeightCorrectionCorrection(String weightCorrectionCorrection) {
        this.weightCorrectionCorrection = weightCorrectionCorrection;
        return this;
    }

    @Override
    public String toString() {
        return "PointOneDto{" +
                "nameMuve='" + nameMuve + '\'' +
                ", nameReper='" + nameReper + '\'' +
                ", height='" + height + '\'' +
                ", sum='" + sum + '\'' +
                ", station='" + station + '\'' +
                ", weight='" + weight + '\'' +
                ", _weight='" + _weight + '\'' +
                ", approximationDto=" + approximationDto +
                ", correction='" + correction + '\'' +
                ", weightCorrection='" + weightCorrection + '\'' +
                ", weightCorrectionCorrection='" + weightCorrectionCorrection + '\'' +
                '}';
    }
}
