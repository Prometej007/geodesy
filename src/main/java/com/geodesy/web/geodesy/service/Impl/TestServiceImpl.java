package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by danul on 15.06.2017.
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test(String a) {
        return a;
    }
}
