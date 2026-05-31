package com.proiect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/universitate";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "radu"; 

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Conexiune eșuată: " + e.getMessage());
        }
        return conn;
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + " id SERIAL PRIMARY KEY,"
                + " name VARCHAR(100) NOT NULL,"
                + " email VARCHAR(100) UNIQUE NOT NULL"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelul 'users' a fost inițializat cu succes în PostgreSQL!");
        } catch (SQLException e) {
            System.out.println("Eroare la crearea tabelului: " + e.getMessage());
        }
    }
}