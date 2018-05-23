package com.geodesy.web.geodesy.model.utils;

import com.geodesy.web.geodesy.model.Poligon;

import java.util.Comparator;

public class PoligonNameComparator implements Comparator<Poligon> {
    @Override
    public int compare(Poligon o1, Poligon o2) {
        if(o1==null||o2==null)
        return 0;
        if(o1.getName()==null||o2.getName()==null)
        return 0;
        try{
            Integer n1 = Integer.valueOf(o1.getName());
            Integer n2 = Integer.valueOf(o2.getName());
            return n1-n2;
        }catch (Exception e){
            return 0;
        }
    }
}
