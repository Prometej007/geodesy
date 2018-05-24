package com.geodesy.web.geodesy.service.Impl.persistence;

import com.geodesy.web.geodesy.model.approximation.Approximation;
import com.geodesy.web.geodesy.repository.ApproximationRepository;
import com.geodesy.web.geodesy.service.persistence.ApproximationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproximationServiceImpl implements ApproximationService {

    @Autowired
    private ApproximationRepository approximationRepository;

    @Override
    public Approximation save(Approximation approximation) {
        return approximationRepository.save(approximation.setId(null));
    }

    @Override
    public Approximation findOne(Long id) {
        return approximationRepository.findOne(id);
    }

    @Override
    public List<Approximation> findAll() {
        return approximationRepository.findAll();
    }
}
