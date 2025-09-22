/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author USUARIO
 */
public class JUSTICIA extends UnicastRemoteObject implements IJusticia {

    protected JUSTICIA() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Cuenta> ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
        System.out.println("JUSTICIA: Recibida solicitud de consulta para CI: " + ci);
        ArrayList<Cuenta> cuentasEncontradas = new ArrayList<>();
        String solicitud = "Buscar:" + ci + "-" + nombres + "-" + apellidos;

        try (Socket socketTCP = new Socket("localhost", 6055); 
                
            PrintWriter salida = new PrintWriter(socketTCP.getOutputStream(), true); //enviar
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()))) { //recibir

            salida.println(solicitud);
            String respuestaTCP = entrada.readLine();

            if (!respuestaTCP.isEmpty()) {
                String[] cuentaData = respuestaTCP.split("-");
                String nroCuenta = cuentaData[0];
                double saldo = Double.parseDouble(cuentaData[1]);
                cuentasEncontradas.add(new Cuenta(Banco.BCP, nroCuenta, saldo, ci, nombres, apellidos));
            }
        } catch (Exception e) {
            System.out.println("ASFI: Error al comunicar con Segip (TCP)");
        }

        System.out.println("ASFI: Devolviendo " + cuentasEncontradas.size() + " cuentas.");
        return cuentasEncontradas;
    }

}
