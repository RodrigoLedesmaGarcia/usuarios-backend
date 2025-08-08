package com.spring.www.usuarios_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nombre no puede ir vacio")
    private String nombre;

    @NotBlank(message = "apellido paterno no puede ir vacio")
    private String apellidoPaterno;

    @NotBlank(message = "apellido materno no puede ir vacio")
    private String apellidoMaterno;

    @NotBlank(message = "correo no puede ir vacio")
    private String correo;

    @NotBlank(message = "numero de contacto no puede ir vacio")
    private String numero;

    @NotBlank(message = "alcaldia no puede ir vacio")
    private String alcaldia;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String numero, String alcaldia) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.numero = numero;
        this.alcaldia = alcaldia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }
}
