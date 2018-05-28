package com.geodesy.web.geodesy.service.persistence;

import com.geodesy.web.geodesy.model.poligon.PoligonReper;

public interface PoligonReperService {
    PoligonReper save(PoligonReper poligonReper);

    PoligonReper findOne(Long id);
}
