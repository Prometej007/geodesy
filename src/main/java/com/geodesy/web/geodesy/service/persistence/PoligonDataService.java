package com.geodesy.web.geodesy.service.persistence;

import com.geodesy.web.geodesy.model.poligon.PoligonData;

public interface PoligonDataService {
    PoligonData save(PoligonData poligonData);

    PoligonData findOne(Long id);
}
