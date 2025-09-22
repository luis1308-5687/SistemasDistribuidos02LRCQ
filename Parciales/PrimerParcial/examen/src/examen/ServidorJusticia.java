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
public class ServidorJusticia {
    public static void main(String[] args) {
        try {
            JUSTICIA just = new JUSTICIA();
            LocateRegistry.createRegistry(1099); 
            Naming.bind("JUSTICIA", just);
            System.out.println(">>> Servidor Justicia iniciado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
