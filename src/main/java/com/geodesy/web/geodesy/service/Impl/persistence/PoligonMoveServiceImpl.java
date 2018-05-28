package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.poligon.PoligonMove;
import com.geodesy.web.geodesy.repository.PoligonMoveRepository;
import com.geodesy.web.geodesy.service.persistence.PoligonMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoligonMoveServiceImpl implements PoligonMoveService {

    @Autowired
    private PoligonMoveRepository poligonMoveRepository;

    @Override
    public PoligonMove save(PoligonMove poligonMove) {
        return poligonMoveRepository.save(poligonMove);
    }

    @Override
    public PoligonMove findOne(Long id) {
        return poligonMoveRepository.findOne(id
        );
    }
}
