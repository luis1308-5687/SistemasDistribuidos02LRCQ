/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author USUARIO
 */
public class ServidorASFI {
    public static void main(String[] args) {
        try {
            ASFI asfi = new ASFI();
            LocateRegistry.createRegistry(1999); // Puerto RMI
            Naming.bind("ASFI", asfi);
            System.out.println(">>> Servidor RMI de ASFI iniciado y listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
