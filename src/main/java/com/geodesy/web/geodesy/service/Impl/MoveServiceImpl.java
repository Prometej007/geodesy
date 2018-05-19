package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.repository.MoveRepository;
import com.geodesy.web.geodesy.service.ApproximationService;
import com.geodesy.web.geodesy.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MoveServiceImpl implements MoveService {

    @Autowired
    private MoveRepository moveRepository;
    @Autowired
    private ApproximationService approximationService;

    @Override
    public Move save(Move move) {
        move.setId(moveRepository.save(move).getId());
        return moveRepository.save(move.setApproximations(move.getApproximations().stream().map(approximation -> approximationService.save(approximation.setMove(move))).collect(toList())));
    }

    @Override
    public Move findOne(Long id) {
        return moveRepository.findOne(id);
    }

    @Override
    public List<Move> findAll() {
        return moveRepository.findAll();
    }
}
