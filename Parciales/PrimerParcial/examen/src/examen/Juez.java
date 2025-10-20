/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class Juez {
    public static void main(String[] args) {
        try {
            // ... (la conexión a los servidores no cambia) ...
            IJusticia just = (IJusticia) Naming.lookup("rmi://192.168.43.127:1099/JUSTICIA");
            IASFI asfi = (IASFI) Naming.lookup("rmi://192.168.43.127:2000/ASFI");

            // ... (la consulta de cuentas no cambia) ...
            ArrayList<Cuenta> cuentas = just.ConsultarCuentas("11021654", "Juan Perez", "Segovia");
            
            if (cuentas.isEmpty()) {
                System.out.println("No se encontraron cuentas.");
                return;
            }

            System.out.println("Cuentas encontradas:");
            for (int i = 0; i < cuentas.size(); i++) {
                System.out.println((i + 1) + ". " + cuentas.get(i));
            }
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nSeleccione el numero de la cuenta a congelar: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();
            
            Cuenta cuentaACongelar = cuentas.get(seleccion - 1);

            // --- LÓGICA DE INTERACCIÓN MODIFICADA ---
            System.out.print("Esta seguro de que desea congelar la cuenta " + cuentaACongelar.getNroCuenta() + " (si/no): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("si")) {
                boolean exito = asfi.CongelarCuenta(cuentaACongelar);

                if (exito) {
                    System.out.println("\nCuenta congelada con exito.");
                } else {
                    System.out.println("\nNo se pudo realizar la congelacion.");
                }
            } else {
                System.out.println("\nOperación cancelada.");
            }
            
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}