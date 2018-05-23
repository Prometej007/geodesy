package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.PoligonData;

public interface PoligonMethod {

    PoligonData calculate(PoligonData poligonData);
    PoligonData getWeight(PoligonData poligonData);


}
