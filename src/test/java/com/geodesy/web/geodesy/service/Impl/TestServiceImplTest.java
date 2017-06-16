//package com.geodesy.web.geodesy.service.Impl;
//
//import com.geodesy.web.geodesy.GeodesyApplication;
//import com.geodesy.web.geodesy.model.TestModel;
//import com.geodesy.web.geodesy.service.TestService;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Created by danul on 15.06.2017.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = GeodesyApplication.class)
//@Transactional
//@Rollback
//@WebAppConfiguration
//public class TestServiceImplTest extends Assert {
//    @Autowired
//    TestService testService;
//
//    @Before
//    public void befor() {
//        testService.save(new TestModel("test", "testCOntent"));
//    }
//
//    @After
//    public void after() {
//        testService.deleteAll();
//    }
//
//    @Test
//    public void save() throws Exception {
//        assertTrue(testService.findAll().isEmpty());
//    }
//
//    @Test
//    public void findAll() throws Exception {
//    }
//
//    @Test
//    public void test1() throws Exception {
//    }
//
//}