package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import com.geodesy.web.geodesy.repository.PoligonReperRepository;
import com.geodesy.web.geodesy.service.persistence.PoligonReperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoligonReperServiceImpl implements PoligonReperService {

    @Autowired
    private PoligonReperRepository poligonReperRepository;

    @Override
    public PoligonReper save(PoligonReper poligonReper) {
        return poligonReperRepository.save(poligonReper);
    }

    @Override
    public PoligonReper findOne(Long id) {
        return poligonReperRepository.findOne(id);
    }
}
