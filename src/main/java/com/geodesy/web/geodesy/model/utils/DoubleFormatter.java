package com.geodesy.web.geodesy.model.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DoubleFormatter {
    private static final NumberFormat FORMAT = new DecimalFormat("#.######");

    public static String format(Double val){
        return FORMAT.format(val);
    }
}
