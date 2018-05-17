package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.CalculationData;

public interface DefaultNetworkService {
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
     * P[i] = 1/stationCount
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

    /**
     * P`[i] = P[i] / sum(P)
     *
     * @param calculationData calculationData with moves without weight`
     * @return calculationData with persisted moves with weight`
     */
    CalculationData getWeightStroke(CalculationData calculationData);

    /**
     * @param calculationData calculationData with fulfilled info necessary for calculation
     * @return calculationData with moves of type CHECK
     */
    CalculationData createCheckMoves(CalculationData calculationData);

    /**
     * H[i]+h[i]
     *
     * @param calculationData to proceed (with all data accept corrections and approximations)
     * @return calculated proceeded point (one step)
     */
    CalculationData calculateApproximation(CalculationData calculationData);

    /**
     * @param calculationData to proceed (with all data accept corrections and approximations)
     * @return calculated proceeded point (full)
     */
    CalculationData calculateApproximationFull(CalculationData calculationData);

    /**
     * @param calculationData calculated data without corrections
     * @return ready calculationData for parsing into table
     */
    CalculationData fulfillCorrections(CalculationData calculationData);
}
