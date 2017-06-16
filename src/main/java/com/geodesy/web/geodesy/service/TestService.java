package com.geodesy.web.geodesy.service;

import com.geodesy.web.geodesy.model.TestModel;

import java.util.List;

/**
 * Created by danul on 15.06.2017.
 */
public interface TestService {
    void save(TestModel testModel);
    List<TestModel> findAll();
    void deleteAll();
    String test(String a);
}
