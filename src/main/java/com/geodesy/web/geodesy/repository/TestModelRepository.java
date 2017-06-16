package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by danul on 15.06.2017.
 */
@Repository
public interface TestModelRepository extends JpaRepository<TestModel, Integer> {
}
