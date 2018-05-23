package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.approximation.ApproximationReper;

import java.util.List;

public interface ReperService {
    ApproximationReper save(ApproximationReper approximationReper);
    ApproximationReper findOne(Long id);
    List<ApproximationReper> findAll();
}
