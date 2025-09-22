/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public class Cuenta implements Serializable {

    private Banco banco;
    private String nroCuenta;
    private double saldo;
    private String ci;
    private String nombres;
    private String apellidos;

    public Cuenta(Banco banco, String nroCuenta, double saldo, String ci, String nombres, String apellidos) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Banco getBanco() {
        return banco;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    @Override
    public String toString() {
        return "Banco: " + banco + ", Nro. Cuenta: " + nroCuenta + ", Saldo: " + saldo + " Bs.";
    }
}
