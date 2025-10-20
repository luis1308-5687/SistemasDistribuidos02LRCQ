/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList; 
/**
 *
 * @author USUARIO
 */
public interface IJusticia extends Remote {
    ArrayList<Cuenta> ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException;  
}
