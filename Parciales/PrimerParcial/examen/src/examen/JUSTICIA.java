/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class JUSTICIA extends UnicastRemoteObject implements IJusticia {

    protected JUSTICIA() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Cuenta> ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
        System.out.println("JUSTICIA: Solicitud de consulta para CI: " + ci);
        
        boolean personaEncontrada = verificarEnSegip(ci, nombres, apellidos);

        if (personaEncontrada) {
            System.out.println("JUSTICIA: Persona encontrada en SEGIP. Consultando cuentas en ASFI...");
            try {
                // AQUÍ ESTÁ LA CORRECCIÓN: Le decimos que busque a ASFI en el puerto 2000
                IASFI asfi = (IASFI) Naming.lookup("rmi://192.168.43.236:2000/ASFI");
                
                ArrayList<Cuenta> cuentas = asfi.ConsultarCuentas(ci);
                
                for(Cuenta c : cuentas) {
                    c.setNombres(nombres);
                    c.setApellidos(apellidos);
                }
                return cuentas;

            } catch (Exception e) {
                System.err.println("JUSTICIA: Error al conectar o consultar ASFI via RMI.");
                e.printStackTrace();
                return new ArrayList<>();
            }
        } else {
            System.out.println("JUSTICIA: Persona no encontrada en SEGIP. Devolviendo lista vacía.");
            return new ArrayList<>();
        }
    }

    private boolean verificarEnSegip(String ci, String nombres, String apellidos) {
        String solicitud = "buscar:" + ci + "-" + nombres + "-" + apellidos;
        try (Socket socketTCP = new Socket("192.168.43.236", 6055);
             PrintWriter salida = new PrintWriter(socketTCP.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()))) {
            
            salida.println(solicitud);
            String respuestaTCP = entrada.readLine();
            System.out.println("JUSTICIA: Respuesta de SEGIP: " + respuestaTCP);
            
            return "resultado:encontrado".equalsIgnoreCase(respuestaTCP);

        } catch (Exception e) {
            System.err.println("JUSTICIA: Error al comunicar con Segip (TCP)");
            e.printStackTrace();
            return false;
        }
    }
}
