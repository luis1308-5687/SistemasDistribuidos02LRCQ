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
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("127.0.0.1");
            Socket s = new Socket(ip, 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("--- Conectado al Sistema de Monitoreo ---");

            while (true) {
                System.out.println("\nMENU:");
                System.out.println("1. Enviar estado de sensor");
                System.out.println("2. Pedir reporte critico");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");
                
                String opcion = scn.nextLine();

                if (opcion.equals("1")) {
                    System.out.print("Ingresa ID del sensor: ");
                    String id = scn.nextLine();
                    System.out.print("Ingresa Estado (bajo/medio/alto): ");
                    String estado = scn.nextLine();

                    // Enviamos ID;ESTADO
                    dos.writeUTF(id + ";" + estado);
                    
                    // Leemos respuesta (Debe ser "OK")
                    String respuesta = dis.readUTF();
                    System.out.println("Servidor: " + respuesta);
                } 
                else if (opcion.equals("2")) {
                    // Enviamos comando reporte
                    dos.writeUTF("reporte");
                    
                    // Leemos respuesta (El sensor crítico)
                    String respuesta = dis.readUTF();
                    System.out.println("REPORTE: " + respuesta);
                } 
                else if (opcion.equals("0")) {
                    dos.writeUTF("salir");
                    break;
                } 
                else {
                    System.out.println("Opción no válida");
                }
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
