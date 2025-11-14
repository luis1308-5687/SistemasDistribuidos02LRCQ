/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author luisc
 */
public class ServidorUniversidad {
    public static void main(String[] args) {
        try {
            Universidad universidad = new Universidad();
            Naming.bind("Universidad", universidad);
            System.out.println(">>> Servidor Universidad iniciado y listo.");
        } catch (Exception e) {
            System.out.println("Error al iniciar el servidor Universidad: " + e.getMessage());
        }
    }
}
