package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Approximation;
import com.geodesy.web.geodesy.repository.ApproximationRepository;
import com.geodesy.web.geodesy.service.ApproximationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproximationServiceImpl implements ApproximationService {

    @Autowired
    private ApproximationRepository approximationRepository;

    @Override
    public Approximation save(Approximation approximation) {
        return approximationRepository.save(approximation);
    }

    @Override
    public Approximation findOne(Long id) {
        return approximationRepository.findOne(id);
    }

    @Override
    public List<Approximation> findAll() {
        return approximationRepository.findAll();
    }
}
