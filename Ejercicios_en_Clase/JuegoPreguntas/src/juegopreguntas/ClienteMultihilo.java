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
import java.util.*;

public class ClienteMultihilo {
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("127.0.0.1"); // cambiar IP según el server
            Socket s = new Socket(ip, 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.print("Ingresa tu nombre: ");
            String nombre = scn.nextLine();
            dos.writeUTF(nombre);

            System.out.println(dis.readUTF());

            while (true) {
                String pregunta = dis.readUTF();
                if (pregunta.equalsIgnoreCase("fin")) {
                    System.out.println("Juego terminado.");
                    break;
                }
                System.out.println("Pregunta: " + pregunta);

                String respuesta = scn.nextLine();
                dos.writeUTF(respuesta);

                System.out.println(dis.readUTF());
            }

            scn.close();
            dis.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
