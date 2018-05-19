package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Reper;
import com.geodesy.web.geodesy.repository.ReperRepository;
import com.geodesy.web.geodesy.service.ReperService;
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
    public Reper save(Reper reper) {
        reper.setId(null);
        LOGGER.info("Reper name to save :"+reper.getName());
        return reperRepository.save(reper);
    }

    @Override
    public Reper findOne(Long id) {
        return reperRepository.findOne(id);
    }

    @Override
    public List<Reper> findAll() {
        return reperRepository.findAll();
    }
}
