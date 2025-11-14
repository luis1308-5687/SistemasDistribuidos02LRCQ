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

public class ServerMultihilo {
    static Map<String, Integer> marcador = Collections.synchronizedMap(new HashMap<>());
    static String[][] preguntas = {
        {"Capital de bolvia ", "sucre"},
        {"5 + 7 ", "12"},
        {"Color del cielo en un dia despejado", "azul"},
        {"Sigla Sistemas Distribuidos", "sis258"}
    };

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Servidor iniciado en puerto 5056...");

        while (true) {
            Socket s = ss.accept();
            System.out.println("Cliente conectado: " + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // Crear un hilo para cada cliente
            Thread t = new ClientHandler(s, dis, dos);
            t.start();
        }
    }
}