/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorBancoMercantil {

    public static void main(String[] args) {
        int puerto = 6001;
        DatabaseManager dbManager = new DatabaseManager();

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println(">>> Servidor UDP de Banco Mercantil iniciado en el puerto " + puerto);

            while (true) {
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteRecibido);

                String solicitud = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength()).trim();
                System.out.println("Mercantil (UDP): Solicitud recibida: \"" + solicitud + "\"");

                String[] partes = solicitud.split(":");
                String comando = partes[0];
                String respuesta = "";

                if (comando.equals("Buscar")) {
                    String ci = partes[1];
                    respuesta = dbManager.buscarCuentasPorCIyBanco(ci, "Mercantil");
                } else if (comando.equals("Congelar")) {
                    String nroCuenta = partes[1];
                    if (nroCuenta.equals("1515")) {
                        respuesta = "SI-congelada";
                    } else {
                        respuesta = "NO-cuenta no encontrada";
                    }
                }

                byte[] bufferSalida = respuesta.getBytes();
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
                socket.send(paqueteRespuesta);
                System.out.println("Mercantil (UDP): Respuesta enviada: \"" + respuesta + "\"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}