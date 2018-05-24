package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.approximation.ApproximationReper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReperRepository extends JpaRepository<ApproximationReper,Long> {
    List<ApproximationReper> findAllByData_Id(Long id);
}
