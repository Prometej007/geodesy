package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.TestModel;
import com.geodesy.web.geodesy.repository.TestModelRepository;
import com.geodesy.web.geodesy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danul on 15.06.2017.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestModelRepository testModelRepository;

    @Override
    public void save(TestModel testModel) {
        testModelRepository.save(testModel);
    }

    @Override
    public List<TestModel> findAll() {
        return testModelRepository.findAll();
    }

    @Override
    public void deleteAll() {
        testModelRepository.deleteAll();
    }

    @Override
    public String test(String a) {
        return a;
    }
}
