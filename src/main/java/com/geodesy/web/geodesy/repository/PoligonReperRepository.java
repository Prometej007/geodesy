package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoligonReperRepository extends JpaRepository<PoligonReper,Long> {
}
