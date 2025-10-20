/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IASFI extends Remote {
    ArrayList<Cuenta> ConsultarCuentas(String ci) throws RemoteException;
    
    // MÉTODO MODIFICADO: Ahora se llama CongelarCuenta y no recibe monto
    boolean CongelarCuenta(Cuenta cuenta) throws RemoteException;
}