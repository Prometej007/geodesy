package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.approximation.CalculationData;

import java.util.List;

public interface CalculationDataService {
    CalculationData save(CalculationData calculationData);
    CalculationData findOne(Long id);
    List<CalculationData> findAll();
}
