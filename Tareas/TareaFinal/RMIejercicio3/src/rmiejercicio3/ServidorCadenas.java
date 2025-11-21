/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmiejercicio3;

/**
 *
 * @author luisc
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorCadenas extends UnicastRemoteObject implements IManejoCadenas {

    private String cadenaActual = "";

    public ServidorCadenas() throws RemoteException {
        super();
    }


    @Override
    public boolean guardarFrase(String frase) throws RemoteException {
        this.cadenaActual = frase;
        System.out.println("Nueva frase guardada: " + this.cadenaActual);
        return true;
    }

    @Override
    public String convertirMayusculas() throws RemoteException {
        this.cadenaActual = this.cadenaActual.toUpperCase();
        return this.cadenaActual;
    }

    @Override
    public String duplicarEspacios(int veces) throws RemoteException {
        if (veces < 1) return this.cadenaActual;
        
        StringBuilder espacios = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            espacios.append(" ");
        }
        
        this.cadenaActual = this.cadenaActual.replace(" ", espacios.toString());
        return this.cadenaActual;
    }

    @Override
    public String concatenar(String extra) throws RemoteException {
        this.cadenaActual = this.cadenaActual + extra;
        return this.cadenaActual;
    }

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(1099);

            ServidorCadenas objetoRemoto = new ServidorCadenas();
            
            registro.rebind("ServicioCadenas", objetoRemoto);
            
            System.out.println("--- Servidor RMI Activo ---");
            System.out.println("Esperando conexiones...");
            
        } catch (Exception e) {
            System.err.println("Error en servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
