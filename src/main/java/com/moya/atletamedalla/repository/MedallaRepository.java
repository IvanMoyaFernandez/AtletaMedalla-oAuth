package com.moya.atletamedalla.repository;

import com.moya.atletamedalla.domain.Medalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedallaRepository extends JpaRepository<Medalla, Long> {
    @Query("SELECT medalla " +
            "FROM Medalla medalla")
    List<Medalla> getAtletasGroupByTipoMedalla();
}

