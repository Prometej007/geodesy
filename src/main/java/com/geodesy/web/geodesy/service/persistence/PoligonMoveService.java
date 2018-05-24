package com.geodesy.web.geodesy.service.persistence;

import com.geodesy.web.geodesy.model.poligon.PoligonMove;

public interface PoligonMoveService {
    PoligonMove save(PoligonMove poligonMove);

    PoligonMove findOne(Long id);
}
