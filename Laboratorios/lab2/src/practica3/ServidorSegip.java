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
public class ServidorSegip {
    
    public static void main(String[] args) {
        try {

            Segip segip = new Segip();
            LocateRegistry.createRegistry(1099);
            Naming.bind("Segip", segip);

            System.out.println(">>> Servidor SEGIP iniciado y listo. Esperando clientes...");
        } catch (Exception e) {
            System.out.println("Error al iniciar el servidor SEGIP: " + e.getMessage());
        }
    }
}
