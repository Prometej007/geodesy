package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.Reper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReperRepository extends JpaRepository<Reper,Long> {
    List<Reper> findAllByCalculationData_Id(Long id);
}
