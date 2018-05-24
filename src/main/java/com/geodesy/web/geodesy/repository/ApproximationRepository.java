package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.approximation.Approximation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproximationRepository extends JpaRepository<Approximation,Long> {
}
