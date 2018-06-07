package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.approximation.ApproximationReper;
import com.geodesy.web.geodesy.repository.ReperRepository;
import com.geodesy.web.geodesy.service.persistence.ReperService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReperServiceImpl implements ReperService {

    private static final Logger LOGGER = Logger.getLogger(ReperServiceImpl.class);

    @Autowired
    private ReperRepository reperRepository;

    @Override
    public ApproximationReper save(ApproximationReper approximationReper) {
        approximationReper.setId(null);
//        LOGGER.info("ApproximationReper name to save :"+ approximationReper.getName());
        return reperRepository.save(approximationReper);
    }

    @Override
    public ApproximationReper findOne(Long id) {
        return reperRepository.findOne(id);
    }

    @Override
    public List<ApproximationReper> findAll() {
        return reperRepository.findAll();
    }
}
