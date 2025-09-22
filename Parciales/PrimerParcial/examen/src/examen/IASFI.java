/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author USUARIO
 */
public interface IASFI extends Remote {
    boolean RetenerMonto(Cuenta cuenta, double monto) throws RemoteException;
}
