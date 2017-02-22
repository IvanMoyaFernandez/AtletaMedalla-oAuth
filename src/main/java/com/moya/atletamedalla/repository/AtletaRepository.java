package com.moya.atletamedalla.repository;

import com.moya.atletamedalla.domain.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Long> {
    public List<Atleta> findByNombre(String nombreAtleta);

    // Buscar Atleta por nombre
    List<Atleta> findByNombreContaining(String nombre);

    // 1. Devolver todos los Atletas de una nacionalidad determinada
    List<Atleta> findByNacionalidadLike(String nacionalidad);

    // 2. Devolver todos los atletas que hayan nacido en una fecha anterior a una fecha determinada.
    List<Atleta> findByfechaNacimientoLessThan(Date fechaNacimiento);

    // 3. Devolver todos los atletas que hayan nacido en una fecha anterior a una fecha determinada.
    @Query("SELECT atleta " +
            "FROM Atleta atleta")
    List<Atleta> getAtletasGroupByNacionalidad();
}
