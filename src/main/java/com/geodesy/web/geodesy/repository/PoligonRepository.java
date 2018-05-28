package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.poligon.Poligon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoligonRepository extends JpaRepository<Poligon,Long> {
}
