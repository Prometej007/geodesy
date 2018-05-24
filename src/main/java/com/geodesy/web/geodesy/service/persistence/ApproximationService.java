package com.geodesy.web.geodesy.service.persistence;

import com.geodesy.web.geodesy.model.approximation.Approximation;

import java.util.List;

public interface ApproximationService {
    Approximation save(Approximation approximation);
    Approximation findOne(Long id);
    List<Approximation> findAll();
}
