package com.geodesy.web.geodesy.repository;

import com.geodesy.web.geodesy.model.poligon.PoligonMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoligonMoveRepository extends JpaRepository<PoligonMove,Long> {
}
