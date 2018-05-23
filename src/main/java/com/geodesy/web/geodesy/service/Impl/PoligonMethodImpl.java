package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Poligon;
import com.geodesy.web.geodesy.model.PoligonData;
import com.geodesy.web.geodesy.model.PoligonMove;
import com.geodesy.web.geodesy.service.PoligonMethod;

import java.util.List;

public class PoligonMethodImpl implements PoligonMethod {
    @Override
    public PoligonData calculate(PoligonData poligonData) {
        return null;
    }

    @Override
    public PoligonData getWeight(PoligonData poligonData) {
        poligonData = getMischief(poligonData);
        poligonData = getPerimeter(poligonData);
        for (Poligon poligon :
                poligonData.getPoligonList()) {
            for (PoligonMove poligonMove :
                    poligon.getPoligonMoves()) {
                poligonMove.setWeight(poligonMove.getDistance() / poligon.getPerimeter());
                poligonMove.setCorrection(poligonMove.getWeight() * poligon.getMischief());
            }
        }
        return poligonData;
    }

    private PoligonData getMischief(PoligonData poligonData) {
        List<Poligon> poligons = poligonData.getPoligonList();
        for (Poligon poligon : poligons) {
            List<PoligonMove> poligonMoves = poligon.getPoligonMoves();
            Double mis = .0;
            for (PoligonMove poligonMove :
                    poligonMoves) {
                mis += poligonMove.getExceeding();
            }
            poligon.setMischief(mis);
        }
        return poligonData.setPoligonList(poligons);
    }

    private PoligonData getPerimeter(PoligonData poligonData) {
        List<Poligon> poligons = poligonData.getPoligonList();
        for (Poligon poligon : poligons) {
            List<PoligonMove> poligonMoves = poligon.getPoligonMoves();
            Double per = .0;
            for (PoligonMove poligonMove :
                    poligonMoves) {
                per += poligonMove.getDistance();
            }
            poligon.setPerimeter(per);
        }
        return poligonData.setPoligonList(poligons);
    }
}
