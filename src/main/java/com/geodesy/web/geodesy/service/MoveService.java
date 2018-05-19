package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.Move;

import java.util.List;

public interface MoveService {
    Move save(Move move);
    Move findOne(Long id);
    List<Move> findAll();
}
