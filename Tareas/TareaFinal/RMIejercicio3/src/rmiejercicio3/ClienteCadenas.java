/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmiejercicio3;

/**
 *
 * @author luisc
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteCadenas {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            IManejoCadenas servicio = (IManejoCadenas) registro.lookup("ServicioCadenas");
            
            System.out.println("--- Conectado al Servidor de Cadenas ---");

            while (true) {
                System.out.println("\nMENU DE OPERACIONES:");
                System.out.println("1. Guardar Frase Nueva");
                System.out.println("2. Convertir a Mayusculas");
                System.out.println("3. Duplicar Espacios");
                System.out.println("4. Concatenar cadena");
                System.out.println("0. Salir");
                System.out.print("Elige opcion: ");
                
                int opcion = sc.nextInt();
                sc.nextLine();

                String resultado = "";

                switch (opcion) {
                    case 1:
                        System.out.print("Escribe la frase: ");
                        String frase = sc.nextLine();
                        boolean ok = servicio.guardarFrase(frase);
                        if(ok) System.out.println("Servidor: Frase guardada con exito.");
                        break;
                        
                    case 2:
                        resultado = servicio.convertirMayusculas();
                        System.out.println("Resultado: " + resultado);
                        break;
                        
                    case 3:
                        System.out.print("Cuantas veces quieres multiplicar los espacios? ");
                        int veces = sc.nextInt();
                        resultado = servicio.duplicarEspacios(veces);
                        System.out.println("Resultado: " + resultado);
                        break;
                        
                    case 4:
                        System.out.print("Que texto quieres agregar al final? ");
                        String extra = sc.nextLine();
                        resultado = servicio.concatenar(extra);
                        System.out.println("Resultado: " + resultado);
                        break;
                        
                    case 0:
                        System.out.println("Saliendo...");
                        return;
                        
                    default:
                        System.out.println("Opcion no valida");
                }
            }

        } catch (Exception e) {
            System.err.println("Error en cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
