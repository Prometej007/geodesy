package com.geodesy.web.geodesy.model.utils;

import com.geodesy.web.geodesy.model.Poligon;

import java.util.Comparator;

public class PoligonMischiefComparator implements Comparator<Poligon> {
    @Override
    public int compare(Poligon o1, Poligon o2) {
        if (o1 == null || o2 == null)
            return 0;
        if (o1.getMischief() == null || o2.getMischief() == null)
            return 0;
        if (Math.abs(o1.getMischief()) > Math.abs(o2.getMischief()))
            return -1;
        else if (Math.abs(o1.getMischief()) < Math.abs(o2.getMischief()))
            return 1;
        return 0;
    }
}
