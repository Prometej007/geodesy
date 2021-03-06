package com.geodesy.web.geodesy.model.utils.constants;

import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;

import java.util.HashMap;

public class CalculationType {
    public static final HashMap<CalculationTypeName, Double> TYPES;
    public static final HashMap<CalculationTypeName, Double> TYPES_P;
    public static final HashMap<CalculationTypeName, Integer> TYPES_SQ;
    private static final Double FIRST = .00000003;
    private static final Double SECOND = .0005;
    private static final Double THIRD = .001;
    private static final Double FOURTH = .002;
    private static final Double FIRST_P = .00003;
    private static final Double SECOND_P = .00005;
    private static final Double THIRD_P = .0001;
    private static final Double FOURTH_P = .0002;
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
        TYPES_P = new HashMap<>();
        TYPES_P.put(CalculationTypeName.FIRST, FIRST_P);
        TYPES_P.put(CalculationTypeName.SECOND, SECOND_P);
        TYPES_P.put(CalculationTypeName.THIRD, THIRD_P);
        TYPES_P.put(CalculationTypeName.FOURTH, FOURTH_P);
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
