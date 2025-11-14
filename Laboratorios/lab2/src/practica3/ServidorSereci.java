/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 *
 * @author luisc
 */
public class ServidorSereci {
    public static void main(String[] args) {
        int puerto = 5003;

        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println(">>> Servidor SERECI iniciado en el puerto " + puerto + ". Esperando paquetes...");
            byte[] bufferEntrada = new byte[1024];
            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteRecibido);
                String solicitud = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("SERECI: Paquete recibido: " + solicitud);


                String[] partes = solicitud.split(":");
                String respuesta;
                if (partes.length == 2 && partes[0].equals("Ver-fecha")) {
                    String[] datos = partes[1].split(",");
                    String fecha = datos[2];
                    if (fecha.equals("13-03-2004")) {
                        respuesta = "verificacion correcta";
                    } else {
                        respuesta = "fecha nacimiento no correcta";
                    }
                } else {
                    respuesta = "Formato incorrecto";
                }

                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();

                byte[] bufferSalida = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                socket.send(paqueteRespuesta);
                System.out.println("SERECI: Respuesta enviada: " + respuesta);
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor SERECI: " + e.getMessage());
        }
    }
    
}
