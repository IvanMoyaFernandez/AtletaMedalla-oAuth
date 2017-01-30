package com.moya.atletamedalla.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.moya.atletamedalla.domain.enumeration.TipoMedalla;

/**
 * A Medalla.
 */
@Entity
@Table(name = "medalla")
public class Medalla implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_medalla", nullable = false)
    private TipoMedalla tipoMedalla;

    @NotNull
    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @NotNull
    @Column(name = "competicion", nullable = false)
    private String competicion;

    @ManyToOne
    private Atleta atleta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMedalla getTipoMedalla() {
        return tipoMedalla;
    }

    public Medalla tipoMedalla(TipoMedalla tipoMedalla) {
        this.tipoMedalla = tipoMedalla;
        return this;
    }

    public void setTipoMedalla(TipoMedalla tipoMedalla) {
        this.tipoMedalla = tipoMedalla;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Medalla especialidad(String especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCompeticion() {
        return competicion;
    }

    public Medalla competicion(String competicion) {
        this.competicion = competicion;
        return this;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public Medalla atleta(Atleta atleta) {
        this.atleta = atleta;
        return this;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medalla medalla = (Medalla) o;
        if (medalla.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, medalla.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Medalla{" +
            "id=" + id +
            ", tipoMedalla='" + tipoMedalla + "'" +
            ", especialidad='" + especialidad + "'" +
            ", competicion='" + competicion + "'" +
            '}';
    }
}
