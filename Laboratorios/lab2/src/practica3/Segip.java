/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author luisc
 */
public class Segip extends UnicastRemoteObject implements ISegip  {

    public Segip() throws RemoteException {
        super();
    }

    @Override
    public boolean Verificar(String ci, String nombres, String primerApellido, String segundoApellido) throws RemoteException {
        System.out.println("SEGIP: solicitud de verificacion para CI: " + ci);
        String apellidosCompletos = primerApellido + " " + segundoApellido;
        boolean esValido = ci.equals("12749454") &&
                           nombres.equals("luis rolando") &&
                           apellidosCompletos.equals("calcina quispe");
        if (esValido) {
            System.out.println("SEGIP: Los datos son correctos.");
        } else {
            System.out.println("SEGIP: Los datos NO son correctos.");
        }
        return esValido;
    }
}