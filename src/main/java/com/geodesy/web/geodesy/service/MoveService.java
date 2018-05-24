package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.approximation.ApproximationMove;

import java.util.List;

public interface MoveService {
    ApproximationMove save(ApproximationMove approximationMove);
    ApproximationMove findOne(Long id);
    List<ApproximationMove> findAll();
}
