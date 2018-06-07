package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.approximation.ApproximationMove;
import com.geodesy.web.geodesy.repository.MoveRepository;
import com.geodesy.web.geodesy.service.persistence.ApproximationService;
import com.geodesy.web.geodesy.service.persistence.MoveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MoveServiceImpl implements MoveService {

    private static final Logger LOGGER = Logger.getLogger(MoveServiceImpl.class);


    @Autowired
    private MoveRepository moveRepository;
    @Autowired
    private ApproximationService approximationService;

    @Override
    public ApproximationMove save(ApproximationMove approximationMove) {
        approximationMove.setId(null);
//        LOGGER.info("ApproximationMove name to save :" + approximationMove.getName());
        approximationMove.setId(moveRepository.save(approximationMove).getId());
        return moveRepository.save(approximationMove.setApproximations(approximationMove.getApproximations().stream().map(approximation -> approximationService.save(approximation.setMove(approximationMove))).collect(toList())));
    }

    @Override
    public ApproximationMove findOne(Long id) {
        return moveRepository.findOne(id);
    }

    @Override
    public List<ApproximationMove> findAll() {
        return moveRepository.findAll();
    }
}
