/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.juegoconsola;

/**
 *
 * @author luisc
 */
import java.util.Scanner;

public class JuegoConsola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Raya juego = new Raya();

        while (true) {
            juego.mostrarTablero();
            System.out.println("Turno del jugador " + juego.getJugadorActual());
            System.out.print("Fila (0-2): ");
            int fila = sc.nextInt();
            System.out.print("Columna (0-2): ");
            int col = sc.nextInt();

            if (!juego.realizarMovimiento(fila, col)) {
                System.out.println("Movimiento inválido, intenta de nuevo.");
                continue;
            }

            if (juego.hayGanador()) {
                juego.mostrarTablero();
                System.out.println("¡Jugador " + juego.getJugadorActual() + " gana!");
                break;
            }

            if (juego.estaLleno()) {
                juego.mostrarTablero();
                System.out.println("¡Empate!");
                break;
            }

            juego.cambiarTurno();
        }

        sc.close();
    }
}
