package com.moya.atletamedalla.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Atleta.
 */
@Entity
@Table(name = "atleta")
public class Atleta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NotNull
    @Column(name = "nacionalidad", nullable = false)
    private String nacionalidad;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "atleta")
    @JsonIgnore
    private Set<Medalla> medallas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Atleta nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Atleta apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Atleta nacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
        return this;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Atleta fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Set<Medalla> getMedallas() {
        return medallas;
    }

    public Atleta medallas(Set<Medalla> medallas) {
        this.medallas = medallas;
        return this;
    }

    public Atleta addMedalla(Medalla medalla) {
        medallas.add(medalla);
        medalla.setAtleta(this);
        return this;
    }

    public Atleta removeMedalla(Medalla medalla) {
        medallas.remove(medalla);
        medalla.setAtleta(null);
        return this;
    }

    public void setMedallas(Set<Medalla> medallas) {
        this.medallas = medallas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Atleta atleta = (Atleta) o;
        if (atleta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, atleta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Atleta{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", apellidos='" + apellidos + "'" +
            ", nacionalidad='" + nacionalidad + "'" +
            ", fechaNacimiento='" + fechaNacimiento + "'" +
            '}';
    }
}
