package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.approximation.CalculationData;

public interface ConsistentApproximationMethod {

    /**
     * @param calculationData data with repers(name,height), moves(name, diff, stCount, dist), type
     * @return calculated data
     */
    CalculationData calculate(CalculationData calculationData);

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
