/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorASFI {
    public static void main(String[] args) {
        try {
            // Puerto que usará este servidor RMI
            int puerto = 2000; // Puedes usar 1999 o el que prefieras

            // 1. Inicia el registro RMI en el puerto especificado
            Registry registry = LocateRegistry.createRegistry(puerto);
            
            System.out.println(">>> Registro RMI iniciado en el puerto " + puerto);

            // 2. Crea la instancia del objeto y la enlaza (bind) al registro
            ASFI asfi = new ASFI();
            registry.bind("ASFI", asfi); // Usamos la referencia 'registry' para enlazar

            System.out.println(">>> Servidor ASFI enlazado y listo.");

        } catch (Exception e) {
            System.err.println("Error en ServidorASFI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
