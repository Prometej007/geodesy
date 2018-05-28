package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.poligon.PoligonData;
import com.geodesy.web.geodesy.repository.PoligonDataRepository;
import com.geodesy.web.geodesy.service.persistence.PoligonDataService;
import com.geodesy.web.geodesy.service.persistence.PoligonReperService;
import com.geodesy.web.geodesy.service.persistence.PoligonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PoligonDataServiceImpl implements PoligonDataService {

    @Autowired
    private PoligonDataRepository poligonDataRepository;
    @Autowired
    private PoligonService poligonService;
    @Autowired
    private PoligonReperService poligonReperService;

    @Override
    public PoligonData save(PoligonData poligonData) {
        poligonData.setId(poligonDataRepository.save(poligonData).getId());
        return poligonDataRepository.save(
                poligonData
                        .setReperList(poligonData.getReperList().stream()
                                .map(poligonReper -> poligonReperService.save(poligonReper.setData(poligonData)))
                                .collect(Collectors.toList()))
                        .setPoligonList(poligonData.getPoligonList().stream()
                                .map(poligon -> poligonService.save(poligon.setData(poligonData)))
                                .collect(Collectors.toList()))
        );
    }

    @Override
    public PoligonData findOne(Long id) {
        return poligonDataRepository.findOne(id);
    }
}
