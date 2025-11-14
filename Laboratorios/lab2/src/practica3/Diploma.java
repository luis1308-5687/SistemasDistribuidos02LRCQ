/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.io.Serializable;

public class Diploma implements Serializable {
    String ci;
    String nombres;
    String primerApellido;
    String segundoApellido;
    String fechaNacimiento;
    String carrera;
    String rude;
    String fechaEmision;
    String mensajeDeError;

    public Diploma(String ci, String nombres, String primerApellido, String segundoApellido, String fechaNacimiento, String carrera, String rude, String fechaEmision) {
        this.ci = ci;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.carrera = carrera;
        this.rude = rude;
        this.fechaEmision = fechaEmision;
        this.mensajeDeError = "";
    }

    public Diploma(String mensajeDeError) {
        this.mensajeDeError = mensajeDeError;
    }

    public String getCi() {
        return ci;
    }

    public String getNombres() {
        return nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getRude() {
        return rude;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public String getMensajeDeError() {
        return mensajeDeError;
    }
}
