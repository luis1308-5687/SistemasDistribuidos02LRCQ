/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class DatabaseManager {
    // --- CAMBIOS PARA MYSQL ---
    private static final String HOST = "localhost";
    private static final String PORT = "3307";
    private static final String DB_NAME = "sistema_judicial";
    private static final String USER = "root"; // <-- CAMBIA ESTO POR TU USUARIO
    private static final String PASS = ""; // <-- CAMBIA ESTO POR TU CONTRASEÑA
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/";
    
    // Método para crear la base de datos y las tablas
    public static void inicializarDB() {
        // Primero, creamos la base de datos si no existe
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Base de datos '" + DB_NAME + "' asegurada.");
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
            return; // Salir si no se puede crear
        }

        // Ahora nos conectamos a la base de datos específica y creamos las tablas
        try (Connection conn = DriverManager.getConnection(URL + DB_NAME, USER, PASS);
             Statement stmt = conn.createStatement()) {
            
            // Tabla para personas (simulando SEGIP)
            String sqlPersonas = "CREATE TABLE IF NOT EXISTS personas ("
                    + "ci VARCHAR(20) PRIMARY KEY,"
                    + "nombres VARCHAR(100) NOT NULL,"
                    + "apellidos VARCHAR(100) NOT NULL"
                    + ");";
            stmt.execute(sqlPersonas);

            // Tabla para cuentas bancarias (simulando Bancos)
            String sqlCuentas = "CREATE TABLE IF NOT EXISTS cuentas ("
                    + "nro_cuenta VARCHAR(50) PRIMARY KEY,"
                    + "banco VARCHAR(50) NOT NULL,"
                    + "saldo DOUBLE NOT NULL,"
                    + "ci_persona VARCHAR(20) NOT NULL,"
                    + "FOREIGN KEY (ci_persona) REFERENCES personas(ci)"
                    + ");";
            stmt.execute(sqlCuentas);

            System.out.println("Tablas creadas correctamente.");

            // Insertar datos de prueba si no existen (usando IGNORE para MySQL)
            stmt.execute("INSERT IGNORE INTO personas (ci, nombres, apellidos) VALUES ('11021654', 'Juan Perez', 'Segovia');");
            stmt.execute("INSERT IGNORE INTO cuentas (nro_cuenta, banco, saldo, ci_persona) VALUES ('1515', 'Mercantil', 5200.0, '11021654');");
            stmt.execute("INSERT IGNORE INTO cuentas (nro_cuenta, banco, saldo, ci_persona) VALUES ('657654', 'BCP', 6000.0, '11021654');");

            System.out.println("Datos de prueba insertados.");

        } catch (SQLException e) {
            System.out.println("Error al crear tablas o insertar datos: " + e.getMessage());
        }
    }

    // Método para verificar si una persona existe (para SEGIP)
    public boolean verificarPersona(String ci, String nombres, String apellidos) {
        String sql = "SELECT COUNT(*) FROM personas WHERE ci = ? AND nombres = ? AND apellidos = ?";
        try (Connection conn = DriverManager.getConnection(URL + DB_NAME, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ci);
            pstmt.setString(2, nombres);
            pstmt.setString(3, apellidos);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para buscar cuentas por CI y banco (para los servidores de bancos)
    public String buscarCuentasPorCIyBanco(String ci, String banco) {
        String sql = "SELECT nro_cuenta, saldo FROM cuentas WHERE ci_persona = ? AND banco = ?";
        StringJoiner joiner = new StringJoiner(":");
        
        try (Connection conn = DriverManager.getConnection(URL + DB_NAME, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ci);
            pstmt.setString(2, banco);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                joiner.add(rs.getString("nro_cuenta") + "-" + rs.getDouble("saldo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }

    // Ejecuta este método una vez para configurar todo
    public static void main(String[] args) {
        inicializarDB();
    }
}