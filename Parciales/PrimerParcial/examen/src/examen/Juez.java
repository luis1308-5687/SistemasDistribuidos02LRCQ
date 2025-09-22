/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author USUARIO
 */
public class Juez {
    public static void main(String[] args) {
        try {
            IJusticia just = (IJusticia) Naming.lookup("rmi://localhost/JUSTICIA");
            IASFI asfi = (IASFI) Naming.lookup("rmi://localhost/ASFI");
            Scanner scanner = new Scanner(System.in);
            ArrayList<Cuenta> cuentas = just.ConsultarCuentas("11021654", "Juan Perez", "Segovia");

            if (cuentas.isEmpty()) {
                System.out.println("No se encontraron cuentas para la persona especificada.");
                return; 
            }

            System.out.println("Cuentas encontradas:");
            for (int i = 0; i < cuentas.size(); i++) {
                System.out.println((i + 1) + ". " + cuentas.get(i));
            }

            System.out.print("\nSeleccione el numero de la cuenta a retener: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine(); 

            
            Cuenta cuentaARetener = cuentas.get(seleccion - 1);

            System.out.print("Ingrese el monto a retener: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            boolean exito = asfi.RetenerMonto(cuentaARetener, monto);

            if (exito) {
                System.out.println("\nretencion realizada");
            } else {
                System.out.println("\nNo se pudo realizar la retencion");
            }
            
            scanner.close();

        } catch (Exception e) {
            System.out.println("Error en el cliente Juez: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
