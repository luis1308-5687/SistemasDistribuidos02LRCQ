/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.Naming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author USUARIO
 */
public class ServidorSegip {

    public static void main(String[] args) {
        int puerto = 6055;
        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println(">>> Servidor TCP SEGIP iniciado en el puerto " + puerto);

            while (true) {
                try (Socket clienteSocket = servidorSocket.accept()) {
                    System.out.println("Segip (TCP): Cliente conectado.");
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream())); //recibir
                    PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true); //enviar

                    String solicitud = entrada.readLine();
                    System.out.println("SEGIP (TCP): Solicitud recibida: " + solicitud);

                    String[] partes = solicitud.split(":");
                    String comando = partes[0];
                    String respuesta = "";

                    if (comando.equals("Buscar")) {
                        String[] datos = partes[1].split("-");
                        String ci = datos[0]; 
                        String nombre = datos[1];
                        String apellido = datos[2]; 
                        if (ci.equals("11021654") && nombre.equals("Juan Perez") && apellido.equals("Segovia")) {
                            try {
                                IJusticia justicia = (IJusticia) Naming.lookup("rmi://localhost/Justicia");

                            } catch (Exception e) {
                                e.printStackTrace();
                                respuesta = "Error en llamada RMI a Justicia";
                            }
                        } else {
                            respuesta = "";
                        }
                    }

                    salida.println(respuesta);
                    System.out.println("Segip (TCP): Respuesta enviada: " + respuesta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
