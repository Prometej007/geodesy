package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.service.DefaultNetworkService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.aspectj.runtime.internal.Conversions.doubleValue;

@Service
public class DefaultNetworkServiceImpl implements DefaultNetworkService {
    @Override
    public Double averageReperWeight(Double weight, Double approximation) {
        return null;
    }

    @Override
    public Double getApproxamation(Double height, Double difference) {
        return null;
    }

    @Override
    public CalculationData getApproximatePointHeight(CalculationData calculationData) {
        return null;
    }

    @Override
    public List<Move> getWeight(List<Move> moveList) {
        Double weight = .0;
        moveList = moveList.stream().map(move -> move.setWeight(doubleValue(1.0 / move.getStationCount()))).collect(toList());
        for (Move move : moveList) {
            weight += move.getWeight();
        }
        Double we = weight;
        return moveList.stream().map(move -> move.setWeightStroke(move.getWeight() / we)).collect(toList());
    }
}
