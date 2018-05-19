package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.Reper;

import java.util.List;

public interface ReperService {
    Reper save(Reper reper);
    Reper findOne(Long id);
    List<Reper> findAll();
}
