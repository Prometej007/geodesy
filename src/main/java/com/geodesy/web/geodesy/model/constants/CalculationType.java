package com.geodesy.web.geodesy.model.constants;

import com.geodesy.web.geodesy.model.enums.CalculationTypeNames;

import java.util.HashMap;

public class CalculationType {
    private static final Double FIRST = .0003;
    private static final Double SECOND = .0005;
    private static final Double THIRD = .001;
    private static final Double FOURTH = .002;

    public static final HashMap<CalculationTypeNames, Double> TYPES;

    static {
        TYPES = new HashMap<>();
        TYPES.put(CalculationTypeNames.FIRST, FIRST);
        TYPES.put(CalculationTypeNames.SECOND, SECOND);
        TYPES.put(CalculationTypeNames.THIRD, THIRD);
        TYPES.put(CalculationTypeNames.FOURTH, FOURTH);
    }
}
