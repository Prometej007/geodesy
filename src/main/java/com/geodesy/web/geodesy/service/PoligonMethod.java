package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.poligon.PoligonData;

public interface PoligonMethod {

    PoligonData calculate(PoligonData poligonData);

    PoligonData getWeight(PoligonData poligonData);

    PoligonData mainCalculation(PoligonData poligonData);

    PoligonData getHeights(PoligonData poligonData);
}
