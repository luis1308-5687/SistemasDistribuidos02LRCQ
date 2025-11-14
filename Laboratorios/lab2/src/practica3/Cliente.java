/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica3;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Cliente: Conectando con el servidor de la Universidad...");
            IUniversidad universidad = (IUniversidad) Naming.lookup("rmi://localhost/Universidad");

            System.out.println("\n INGRESO DE DATOS DEL ESTUDIANTE ");
            System.out.print("Ingrese el CI: ");
            String ci = scanner.nextLine();
            System.out.print("Ingrese los Nombres: ");
            String nombres = scanner.nextLine();
            System.out.print("Ingrese el Primer Apellido: ");
            String primerApellido = scanner.nextLine();
            System.out.print("Ingrese el Segundo Apellido: ");
            String segundoApellido = scanner.nextLine();
            System.out.print("Ingrese la Fecha de Nacimiento (dd-mm-aaaa): ");
            String fechaNacimiento = scanner.nextLine();
            System.out.print("Ingrese la Carrera: ");
            String carrera = scanner.nextLine();
            System.out.println("\n Solicitando la diploma");

            Diploma miDiploma = universidad.EmitirDiploma(
                    ci,
                    nombres,
                    primerApellido,
                    segundoApellido,
                    fechaNacimiento,
                    carrera
            );
            if (miDiploma.getMensajeDeError().isEmpty()) {
                System.out.println(" Fecha: " + miDiploma.getFechaEmision());
                System.out.println(" CI: " + miDiploma.getCi());
                System.out.println(" Nombres: " + miDiploma.getNombres());
                System.out.println(" Primer Apellido: " + miDiploma.getPrimerApellido());
                System.out.println(" Segundo Apellido: " + miDiploma.getSegundoApellido());
                System.out.println(" Fecha de Nacimiento: " + miDiploma.getFechaNacimiento());
                System.out.println(" Carrera: " + miDiploma.getCarrera());
                System.out.println(" RUDE: " + miDiploma.getRude());
            } else {
                System.out.println("\nNo se pudo emitir el diploma.");
                System.out.println("Motivos: " + miDiploma.getMensajeDeError());
            }

        } catch (Exception e) {
            System.out.println("Error fatal en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
