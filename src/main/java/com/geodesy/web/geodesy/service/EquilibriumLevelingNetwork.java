package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.CalculationData;

public interface EquilibriumLevelingNetwork {
    /**
     * @param weight P
     * @param approximation H[i]
     * @return H'
     */
    Double averageReperWeight(Double weight, Double approximation);

    /**
     * @param height H[i]
     * @param difference h[i]
     * @return H[i+1]
     */
    Double getApproxamation(Double height, Double difference);

    /**
     * @param calculationData calculationData with only repers' known height
     * @return persisted calculationData with approximate height of points
     */
    CalculationData getApproximatePointHeight(CalculationData calculationData);
    Double getWeight();
}
