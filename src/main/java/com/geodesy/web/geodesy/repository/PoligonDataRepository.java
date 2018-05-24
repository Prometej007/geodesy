package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.poligon.PoligonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoligonDataRepository extends JpaRepository<PoligonData,Long> {
}
