/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ASFI extends UnicastRemoteObject implements IASFI {

    protected ASFI() throws RemoteException {
        super();
    }

    // MÉTODO IMPLEMENTADO: Aquí está la lógica de consulta a los bancos.
    @Override
    public ArrayList<Cuenta> ConsultarCuentas(String ci) throws RemoteException {
        System.out.println("ASFI: Recibida solicitud para CI: " + ci);
        ArrayList<Cuenta> cuentasEncontradas = new ArrayList<>();

        // Consultar a ambos bancos (Mercantil y BCP)
        consultarBancoUDP(cuentasEncontradas, ci, "Mercantil", 6001);
        consultarBancoUDP(cuentasEncontradas, ci, "BCP", 6002);

        System.out.println("ASFI: Devolviendo " + cuentasEncontradas.size() + " cuentas.");
        return cuentasEncontradas;
    }

    private void consultarBancoUDP(ArrayList<Cuenta> listaCuentas, String ci, String nombreBanco, int puerto) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(2000); // Timeout de 2 segundos para no esperar indefinidamente
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            String mensaje = "Buscar:" + ci;

            // Enviar solicitud
            byte[] bufferSalida = mensaje.getBytes();
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puerto);
            socket.send(paqueteSalida);

            // Recibir respuesta
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteEntrada);

            String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength()).trim();
            System.out.println("ASFI: Respuesta recibida de " + nombreBanco + ": '" + respuesta + "'");

            // Procesar la respuesta si no está vacía
            if (!respuesta.isEmpty()) {
                String[] cuentas = respuesta.split(":");
                for (String cuentaStr : cuentas) {
                    String[] datos = cuentaStr.split("-");
                    String nroCuenta = datos[0];
                    double saldo = Double.parseDouble(datos[1]);
                    Banco bancoEnum = Banco.valueOf(nombreBanco);
                    // Nota: No tenemos nombres/apellidos aquí, ASFI solo maneja cuentas.
                    listaCuentas.add(new Cuenta(bancoEnum, nroCuenta, saldo, ci, "", ""));
                }
            }
        } catch (SocketTimeoutException e) {
            System.err.println("ASFI: Timeout al conectar con el banco " + nombreBanco);
        } catch (Exception e) {
            System.err.println("ASFI: Error al comunicar con el banco " + nombreBanco);
            e.printStackTrace();
        }
    }

    @Override
    public boolean CongelarCuenta(Cuenta cuenta) throws RemoteException {
        System.out.println("ASFI: Solicitud para CONGELAR la cuenta " + cuenta.getNroCuenta());
        
        int puertoBanco = (cuenta.getBanco() == Banco.Mercantil) ? 6001 : 6002;

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(2000);
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            
            // Mensaje UDP simplificado, sin monto
            String mensaje = "Congelar:" + cuenta.getNroCuenta();
            
            byte[] bufferSalida = mensaje.getBytes();
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puertoBanco);
            socket.send(paqueteSalida);

            // Recibir respuesta del banco
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteEntrada);

            String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength()).trim();
            System.out.println("ASFI: Respuesta de congelacion del banco: '" + respuesta + "'");

            return respuesta.startsWith("SI");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
