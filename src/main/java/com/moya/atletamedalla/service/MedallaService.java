package com.moya.atletamedalla.service;

import com.moya.atletamedalla.domain.Atleta;
import com.moya.atletamedalla.domain.Medalla;
import com.moya.atletamedalla.domain.enumeration.TipoMedalla;
import com.moya.atletamedalla.repository.AtletaRepository;
import com.moya.atletamedalla.repository.MedallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedallaService {
    @Autowired
    private MedallaRepository medallaRepository;
    @Autowired
    private AtletaRepository atletaRepository;

    // CREAMOS LAS MEDALLAS
/*
    public void registrarMedallas() {
        Atleta atleta;
        Medalla medalla1 = new Medalla();
        medalla1.setTipoMedalla(TipoMedalla.BRONCE);
        medalla1.setEspecialidad("Masculino");
        medalla1.setCompeticion("Basquet");
        atleta = atletaRepository.findByNombre("Ivan").get(0);
        medalla1.setAtleta(atleta);
        medallaRepository.save(medalla1);

        Medalla medalla2 = new Medalla();
        medalla2.setTipoMedalla(TipoMedalla.ORO);
        medalla2.setEspecialidad("Masculino");
        medalla2.setCompeticion("Basquet");
        atleta = atletaRepository.findByNombre("Jose").get(0);
        medalla2.setAtleta(atleta);
        medallaRepository.save(medalla2);

        Medalla medalla3 = new Medalla();
        medalla3.setTipoMedalla(TipoMedalla.PLATA);
        medalla3.setEspecialidad("Masculino");
        medalla3.setCompeticion("Basquet");
        atleta = atletaRepository.findByNombre("Pepe").get(0);
        medalla3.setAtleta(atleta);
        medallaRepository.save(medalla3);

        Medalla medalla4 = new Medalla();
        medalla4.setTipoMedalla(TipoMedalla.PLATA);
        medalla4.setEspecialidad("Masculino");
        medalla4.setCompeticion("Basquet");
        atleta = atletaRepository.findByNombre("Carlos").get(0);
        medalla4.setAtleta(atleta);
        medallaRepository.save(medalla4);
    }
*/
}
