/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSeduca {
    public static void main(String[] args) {
        int puerto = 5002;

        try {
            ServerSocket servidorSocket = new ServerSocket(puerto);
            System.out.println(">>> Servidor SEDUCA iniciado en el puerto " + puerto + ". Esperando clientes...");

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("SEDUCA: Cliente conectado.");

                BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

                String solicitud = entrada.readLine();
                System.out.println("SEDUCA: Solicitud recibida: " + solicitud);

                String[] partes = solicitud.split("-");
                String respuesta;

                if (partes.length == 3 && partes[0].equals("verificar") && partes[1].equals("rude")) {
                    String rude = partes[2];
                    
                    if (rude.equals("lucaqu13032004")) {
                        respuesta = "respuesta:si:verificado con exito";
                    } else {
                        respuesta = "respuesta:no:no se encontro el titulo de bachiller";
                    }
                } else {

                    respuesta = "respuesta:no:Formato de solicitud incorrecto";
                }

                salida.println(respuesta);
                System.out.println("SEDUCA: Respuesta enviada: " + respuesta);

                clienteSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor SEDUCA: " + e.getMessage());
        }
    }
}  

