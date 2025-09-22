/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author USUARIO
 */
public class Respuesta implements Serializable{
    private boolean error;
    private String Mensaje;
    ArrayList<Cuenta> cuentasEncontradas = new ArrayList<>();
}
