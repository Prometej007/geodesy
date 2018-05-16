package com.geodesy.web.geodesy.model.constants;

import java.util.HashMap;
import java.util.Map;

public class CalculationType {
    public static final Double FIRST = .001;

    public static final Map<String, Double> TYPES = new HashMap<>();

    static {
        TYPES.put("1", FIRST);
    }
}
