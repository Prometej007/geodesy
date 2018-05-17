package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move,Long> {
    List<Move> findAllByCalculationData_Id(Long id);
}
