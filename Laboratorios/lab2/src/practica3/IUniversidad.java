/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author luisc
 */
public interface IUniversidad extends Remote{
    
    public  Diploma EmitirDiploma(String ci, String nombres, String primerApellido, String segundoApellido, String fechaNacimiento, String carrera) throws RemoteException;
    
    
}