package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.approximation.CalculationData;

public interface DefaultConfiguration {
    /**
     * @param calculationData calculationData that comes from file
     * @return calculationData with fulfilled repers and moves
     */
    CalculationData normilize(CalculationData calculationData);

    /**
     * @param calculationData calculationData with only repers' known height
     * @return persisted calculationData with approximate height of points
     */
    CalculationData getApproximatePointHeight(CalculationData calculationData);

    /**
     *
     * @param calculationData calculationData moves without weight
     * @return calculationData with persisted moves with weight
     */
    CalculationData getWeight(CalculationData calculationData);

    /**
     * @param calculationData calculationData which contains not all necessary moves (e.g has "1-2" but no "2-1")
     * @return calculationData which contains all necessary moves
     */
    CalculationData fulfillMoves(CalculationData calculationData);

}
