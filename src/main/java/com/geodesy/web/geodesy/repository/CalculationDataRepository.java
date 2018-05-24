package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.approximation.CalculationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationDataRepository extends JpaRepository<CalculationData,Long> {

}
