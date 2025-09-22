/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author USUARIO
 */
public class ASFI extends UnicastRemoteObject implements IASFI {

    protected ASFI() throws RemoteException {
        super();
    }

    @Override
    public boolean RetenerMonto(Cuenta cuenta, double monto) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
