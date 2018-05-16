package com.geodesy.web.geodesy.model.utils;

import com.geodesy.web.geodesy.model.Move;

import java.util.Comparator;

public class MoveNameComparator implements Comparator<Move> {
    @Override
    public int compare(Move o1, Move o2) {
        if (o1 == null || o2 == null)
            return 0;
        if ((o1.getName() == null || o1.getName().equals("")) ||
                (o2.getName() == null || o2.getName().equals("")))
            return 0;
        try {
            Integer o1n = Integer.valueOf(o1.getName().charAt(o1.getName().length() - 1));
            Integer o2n = Integer.valueOf(o2.getName().charAt(o2.getName().length() - 1));
            return o1n - o2n;
        } catch (Exception e) {
            return 0;
        }
    }
}
