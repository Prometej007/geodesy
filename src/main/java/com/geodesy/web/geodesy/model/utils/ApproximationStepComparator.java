package com.geodesy.web.geodesy.model.utils;

import com.geodesy.web.geodesy.model.Approximation;

import java.util.Comparator;

public class ApproximationStepComparator implements Comparator<Approximation> {
    @Override
    public int compare(Approximation o1, Approximation o2) {
        if (o1 == null || o2 == null)
            return 0;
        if (o1.getStep() == null || o2.getStep() == null)
            return 0;
        return o1.getStep() - o2.getStep();
    }
}
