package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.poligon.Poligon;
import com.geodesy.web.geodesy.repository.PoligonRepository;
import com.geodesy.web.geodesy.service.persistence.PoligonMoveService;
import com.geodesy.web.geodesy.service.persistence.PoligonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PoligonServiceImpl implements PoligonService {

    @Autowired
    private PoligonRepository poligonRepository;
    @Autowired
    private PoligonMoveService poligonMoveService;

    @Override
    public Poligon save(Poligon poligon) {
        poligon.setId(poligonRepository.save(poligon).getId());
        return poligonRepository.save(poligon.setPoligonMoves(poligon.getPoligonMoves().stream().map(poligonMove -> poligonMoveService.save(poligonMove.setPoligon(poligon))).collect(Collectors.toList())));
    }

    @Override
    public Poligon findOne(Long id) {
        return null;
    }
}
