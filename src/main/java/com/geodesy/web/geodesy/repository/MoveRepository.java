package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.approximation.ApproximationMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<ApproximationMove,Long> {
    List<ApproximationMove> findAllByCalculationData_Id(Long id);
}
