package com.moya.atletamedalla.service;

import com.moya.atletamedalla.domain.Atleta;
import com.moya.atletamedalla.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
@Transactional
public class AtletaService {
    @Autowired
    private AtletaRepository atletaRepository;
    Calendar calendar = GregorianCalendar.getInstance();

    // CREAMOS LOS ATLETAS
/*
    public void registrarAtletas() {
        Atleta atleta1 = new Atleta();
        atleta1.setNombre("Ivan");
        atleta1.setApellidos("Moya");
        calendar.set(1995, Calendar.AUGUST, 11);
        atleta1.setFechaNacimiento(calendar.getTime());
        atleta1.setNacionalidad("Es");
        atletaRepository.save(atleta1);

        Atleta atleta2 = new Atleta();
        atleta2.setNombre("Carlos");
        atleta2.setApellidos("Garcia");
        calendar.set(1993, Calendar.APRIL, 20);
        atleta2.setFechaNacimiento(calendar.getTime());
        atleta2.setNacionalidad("Por");
        atletaRepository.save(atleta2);

        Atleta atleta3 = new Atleta();
        atleta3.setNombre("Jose");
        atleta3.setApellidos("Dorado");
        calendar.set(1994, Calendar.NOVEMBER, 5);
        atleta3.setFechaNacimiento(calendar.getTime());
        atleta3.setNacionalidad("Es");
        atletaRepository.save(atleta3);

        Atleta atleta4 = new Atleta();
        atleta4.setNombre("Pepe");
        atleta4.setApellidos("Fernandez");
        calendar.set(1997, Calendar.JULY, 7);
        atleta4.setFechaNacimiento(calendar.getTime());
        atleta4.setNacionalidad("Por");
        atletaRepository.save(atleta4);
    }
*/
}
