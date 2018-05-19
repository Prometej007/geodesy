package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.repository.CalculationDataRepository;
import com.geodesy.web.geodesy.service.CalculationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationDataServiceImpl implements CalculationDataService {

    @Autowired
    private CalculationDataRepository calculationDataRepository;

    @Override
    public CalculationData save(CalculationData calculationData) {
        return calculationDataRepository.save(calculationData);
    }

    @Override
    public CalculationData findOne(Long id) {
        return calculationDataRepository.findOne(id);
    }

    @Override
    public List<CalculationData> findAll() {
        return calculationDataRepository.findAll();
    }
}
