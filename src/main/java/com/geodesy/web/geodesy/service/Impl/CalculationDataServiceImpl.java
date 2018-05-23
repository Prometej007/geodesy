package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.repository.CalculationDataRepository;
import com.geodesy.web.geodesy.service.CalculationDataService;
import com.geodesy.web.geodesy.service.MoveService;
import com.geodesy.web.geodesy.service.ReperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculationDataServiceImpl implements CalculationDataService {

    @Autowired
    private CalculationDataRepository calculationDataRepository;
    @Autowired
    private MoveService moveService;
    @Autowired
    private ReperService reperService;

    @Override
    public CalculationData save(CalculationData calculationData) {
        calculationData.setId(calculationDataRepository.save(calculationData).getId());
        return calculationDataRepository.save(calculationData
                .setApproximationMoveList(calculationData.getApproximationMoveList().stream().map(move -> moveService.save(move.setCalculationData(calculationData))).collect(Collectors.toList()))
                .setReperList(calculationData.getApproximationReperList().stream().map(reper -> reperService.save(reper.setCalculationData(calculationData))).collect(Collectors.toList()))
        );
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
