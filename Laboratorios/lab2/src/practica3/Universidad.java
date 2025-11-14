/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Universidad extends UnicastRemoteObject implements IUniversidad {

    public Universidad() throws RemoteException {
        super();
    }

    @Override
    public Diploma EmitirDiploma(String ci, String nombres, String primerApellido, String segundoApellido, String fechaNacimiento, String carrera) throws RemoteException {
        List<String> errores = new ArrayList<>();

        consultarSegip(ci, nombres, primerApellido, segundoApellido, errores);

        String rude = calcularRude(nombres, primerApellido, segundoApellido, fechaNacimiento);
        consultarSeduca(rude, errores);

        consultarSereci(nombres, primerApellido + " " + segundoApellido, fechaNacimiento, errores);

        if (errores.isEmpty()) {
            System.out.println("Universidad: Todas las verificaciones fueron exitosas. Emitiendo diploma completo.");
            String fechaHoy = "04 de septiembre de 2025";

            return new Diploma(ci, nombres, primerApellido, segundoApellido, fechaNacimiento, carrera, rude, fechaHoy);

        } else {
            System.out.println("Universidad: No se emitira el diploma.");
            String mensajeFinalError = String.join(" | ", errores);

            return new Diploma(mensajeFinalError);
        }
    }

    private void consultarSegip(String ci, String nombres, String primerApellido, String segundoApellido, List<String> errores) {
        try {
            System.out.println("Universidad: Conectando con SEGIP...");
            ISegip segip = (ISegip) Naming.lookup("rmi://localhost/Segip");
            boolean esValido = segip.Verificar(ci, nombres, primerApellido, segundoApellido);

            if (!esValido) {
                errores.add("Los Datos del CI no son correctos");
            }
        } catch (Exception e) {
            System.out.println("Error de comunicación con SEGIP: " + e.getMessage());
            errores.add("No se pudo conectar con el servidor de SEGIP");
        }
    }

    private void consultarSeduca(String rude, List<String> errores) {
        try (Socket socket = new Socket("localhost", 5002); PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Universidad: Conectando con SEDUCA...");
            salida.println("verificar-rude-" + rude);

            String respuesta = entrada.readLine();
            String[] partes = respuesta.split(":");

            if (partes[1].equals("no")) {
                errores.add(partes[2]);
            }
        } catch (Exception e) {
            System.out.println("Error de comunicacion con SEDUCA: " + e.getMessage());
            errores.add("No se pudo conectar con el servidor de SEDUCA");
        }
    }

    private void consultarSereci(String nombres, String apellidos, String fechaNac, List<String> errores) {
        try (DatagramSocket socket = new DatagramSocket()) {
            System.out.println("Universidad: Conectando con SERECI...");
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            String mensaje = "Ver-fecha:" + nombres + "," + apellidos + "," + fechaNac;

            byte[] bufferSalida = mensaje.getBytes();
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, 5003);
            socket.send(paqueteSalida);

            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteEntrada);

            String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            String[] partes = respuesta.split(":");

            if (partes[0].equals("no")) {
                errores.add(partes[1]);
            }
        } catch (Exception e) {
            System.out.println("Error de comunicacion con SERECI: " + e.getMessage());
            errores.add("No se pudo conectar con el servidor de SERECI");
        }
    }

    private String calcularRude(String nombres, String primerApellido, String segundoApellido, String fechaNacimiento) {
        String p1 = nombres.substring(0, 2);
        String p2 = primerApellido.substring(0, 2);
        String p3 = segundoApellido.substring(0, 2);
        String fechaFormateada = fechaNacimiento.replace("-", "");
        return p1 + p2 + p3 + fechaFormateada;
    }
}
