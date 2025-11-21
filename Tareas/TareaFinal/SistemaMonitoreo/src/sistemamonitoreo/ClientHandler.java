/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemamonitoreo;

/**
 *
 * @author luisc
 */
import java.io.*;
import java.net.*;
import java.util.*;

class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    private Map<String, String> misSensores = new HashMap<>();

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            while (true) {

                String mensaje = dis.readUTF();

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cliente " + s + " envió salir.");
                    break;
                }

                if (mensaje.equalsIgnoreCase("reporte")) {
                    String reporte = generarReporte();
                    dos.writeUTF(reporte);
                }
                else if (mensaje.contains(";")) {
                    String[] partes = mensaje.split(";");
                    if (partes.length == 2) {
                        String id = partes[0].trim();
                        String estado = partes[1].trim().toLowerCase();

                        misSensores.put(id, estado);
 
                        dos.writeUTF("OK");
                    } else {
                        dos.writeUTF("ERROR: Formato incorrecto.");
                    }
                } 
                else {
                    dos.writeUTF("Comando no reconocido.");
                }
            }

            this.dis.close();
            this.dos.close();
            this.s.close();

        } catch (EOFException e) {
            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generarReporte() {
        if (misSensores.isEmpty()) {
            return "No hay sensores registrados aun.";
        }

        // Prioridad: ALTO > MEDIO > BAJO
        
        for (Map.Entry<String, String> entry : misSensores.entrySet()) {
            if (entry.getValue().equals("alto")) {
                return "CRITICO: El sensor " + entry.getKey() + " esta en estado ALTO";
            }
        }

        for (Map.Entry<String, String> entry : misSensores.entrySet()) {
            if (entry.getValue().equals("medio")) {
                return "PRECAUCION: El sensor " + entry.getKey() + " esta en estado MEDIO";
            }
        }

        Map.Entry<String, String> primer = misSensores.entrySet().iterator().next();
        return "NORMAL: Sensor " + primer.getKey() + " en estado " + primer.getValue();
    }
}
