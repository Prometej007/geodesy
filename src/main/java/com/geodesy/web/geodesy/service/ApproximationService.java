package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.Approximation;

import java.util.List;

public interface ApproximationService {
    Approximation save(Approximation approximation);
    Approximation findOne(Long id);
    List<Approximation> findAll();
}
