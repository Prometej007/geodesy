package com.geodesy.web.geodesy.model.constants;

import com.geodesy.web.geodesy.model.enums.CalculationTypeName;

import java.util.HashMap;

public class CalculationType {
    public static final HashMap<CalculationTypeName, Double> TYPES;
    public static final HashMap<CalculationTypeName, Integer> TYPES_SQ;
    private static final Double FIRST = .00001;
    private static final Double SECOND = .0005;
    private static final Double THIRD = .001;
    private static final Double FOURTH = .002;
    private static final Integer FIRST_SQ = 3;
    private static final Integer SECOND_SQ = 5;
    private static final Integer THIRD_SQ = 10;
    private static final Integer FOURTH_SQ = 20;

    static {
        TYPES = new HashMap<>();
        TYPES.put(CalculationTypeName.FIRST, FIRST);
        TYPES.put(CalculationTypeName.SECOND, SECOND);
        TYPES.put(CalculationTypeName.THIRD, THIRD);
        TYPES.put(CalculationTypeName.FOURTH, FOURTH);
        TYPES_SQ = new HashMap<>();
        TYPES_SQ.put(CalculationTypeName.FIRST, FIRST_SQ);
        TYPES_SQ.put(CalculationTypeName.SECOND, SECOND_SQ);
        TYPES_SQ.put(CalculationTypeName.THIRD, THIRD_SQ);
        TYPES_SQ.put(CalculationTypeName.FOURTH, FOURTH_SQ);
    }

    public static Double getSqTypeWeignt(Double distance, CalculationTypeName typeName) {
        return 1 / Math.pow(2, getSqAvgMistake(distance, typeName));
    }

    public static Double getSqAvgMistake(Double distance, CalculationTypeName typeName) {
        return TYPES_SQ.get(typeName) * Math.sqrt(distance);
    }
}
