package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;

import java.util.List;

public interface DefaultNetworkService {
    //move

    /**
     * @param weight        P
     * @param approximation H[i]
     * @return H'
     */
    Double averageReperWeight(Double weight, Double approximation);

    /**
     * @param height     H[i]
     * @param difference h[i]
     * @return H[i+1]
     */
    Double getApproxamation(Double height, Double difference);
    //move-----------------------------------------------------------------

    /**
     * @param calculationData calculationData with only repers' known height
     * @return persisted calculationData with approximate height of points
     */
    CalculationData getApproximatePointHeight(CalculationData calculationData);

    /**
     * P[i] = 1/stationCount
     * P`[i] = P[i] / sum(P)
     * @param moveList moves without weight
     * @return persisted moves with weight and weightStroke
     */
    List<Move> getWeight(List<Move> moveList);
}
