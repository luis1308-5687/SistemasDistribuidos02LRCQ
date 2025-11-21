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

public class ServidorMultihilo {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("--- Servidor de Monitoreo (Puerto 5056) ---");
        System.out.println("Esperando clientes...");

        while (true) {
            Socket s = ss.accept();
            System.out.println("Nuevo cliente conectado: " + s);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Thread t = new ClientHandler(s, dis, dos);
            t.start();
        }
    }
}
