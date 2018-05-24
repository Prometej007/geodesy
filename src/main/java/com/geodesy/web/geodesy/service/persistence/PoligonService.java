package com.geodesy.web.geodesy.service.persistence;

import com.geodesy.web.geodesy.model.poligon.Poligon;

public interface PoligonService {
    Poligon save(Poligon poligon);

    Poligon findOne(Long id);
}
