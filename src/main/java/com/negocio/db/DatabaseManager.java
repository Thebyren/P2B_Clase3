package com.negocio.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:foodnet.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            inicializarTablas();
        }
        return connection;
    }

    private static void inicializarTablas() {
        try (Statement stmt = connection.createStatement()) {
            // Crear tabla productos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS productos (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL UNIQUE,
                    precio REAL NOT NULL,
                    stock INTEGER NOT NULL
                )
            """);

            // Crear tabla clientes
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS clientes (
                    id INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    telefono TEXT
                )
            """);

            // Crear tabla pedidos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pedidos (
                    id INTEGER PRIMARY KEY,
                    cliente_id INTEGER,
                    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    total REAL,
                    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
                )
            """);

            // Crear tabla de unión pedido_productos
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pedido_productos (
                    pedido_id INTEGER,
                    producto_id INTEGER,
                    cantidad INTEGER,
                    PRIMARY KEY (pedido_id, producto_id),
                    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
                    FOREIGN KEY (producto_id) REFERENCES productos(id)
                )
            """);

        } catch (SQLException e) {
            System.err.println("Error al inicializar tablas: " + e.getMessage());
        }
    }

    public static void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
    public static String writeQuery(Object data){
        try {
            connection = getConnection();
            String query = "INSERT INTO " + data.getClass().getSimpleName() + " VALUES (";
            for (int i = 0; i < data.getClass().getDeclaredFields().length; i++) {
                query += "?";
                if (i < data.getClass().getDeclaredFields().length - 1) {
                    query += ", ";
                }
            }
            query += ")";
            return query;
        } catch (Exception e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
        return null;
    }
}
