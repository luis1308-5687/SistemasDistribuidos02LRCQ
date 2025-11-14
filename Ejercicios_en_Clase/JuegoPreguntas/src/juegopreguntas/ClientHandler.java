/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JuegoPreguntas;

/**
 *
 * @author luis
 */
import java.io.*;
import java.net.*;


class ClientHandler extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    String nombre;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            nombre = dis.readUTF();
            ServerMultihilo.marcador.put(nombre, 0);

            dos.writeUTF("Bienvenido " + nombre);

            for (String[] pq : ServerMultihilo.preguntas) {
                dos.writeUTF(pq[0]); 
                String respuesta = dis.readUTF();

                if (respuesta.equalsIgnoreCase(pq[1])) {
                    int puntos = ServerMultihilo.marcador.get(nombre) + 1;
                    ServerMultihilo.marcador.put(nombre, puntos);
                    dos.writeUTF("Correcto! Tu puntaje: " + puntos + "\nMarcador: " + ServerMultihilo.marcador);
                } else {
                    dos.writeUTF("Incorrecto. La respuesta era: " + pq[1] +
                                 "\nTu puntaje: " + ServerMultihilo.marcador.get(nombre) +
                                 "\nMarcador: " + ServerMultihilo.marcador);
                }
            }

            dos.writeUTF("fin");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
