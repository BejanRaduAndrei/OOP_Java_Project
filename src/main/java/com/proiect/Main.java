package com.proiect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("=== INIȚIALIZARE BAZĂ DE DATE ===");
        // 1. Mai întâi verificăm/creăm tabelele în PostgreSQL
        createPgAdminTables();
        printPgAdminTables();

        System.out.println("\n=== PORNIRE INTERFAȚĂ GRAFICĂ ===");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/interface.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Sistem Management Vehicule");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show(); // Deschide efectiv fereastra
    }

    public static void main(String[] args) {
        // Metoda launch va apela automat metoda start() de mai sus
        launch(args);
    }

    private static void createPgAdminTables() {
        List<String> statements = List.of(
            "CREATE TABLE IF NOT EXISTS branch (" +
                " id SERIAL PRIMARY KEY," +
                " name VARCHAR(100) NOT NULL," +
                " address VARCHAR(200) NOT NULL," +
                " phone_number VARCHAR(20)" +
                ");",
            "CREATE TABLE IF NOT EXISTS staff (" +
                " id SERIAL PRIMARY KEY," +
                " name VARCHAR(100) NOT NULL," +
                " role VARCHAR(50) NOT NULL," +
                " branch_name VARCHAR(100)," +
                " email VARCHAR(100)" +
                ");",
            "CREATE TABLE IF NOT EXISTS vehicle (" +
                " id SERIAL PRIMARY KEY," +
                " make VARCHAR(50) NOT NULL," +
                " model VARCHAR(50) NOT NULL," +
                " year INT NOT NULL," +
                " daily_rate NUMERIC(10,2) NOT NULL," +
                " available BOOLEAN NOT NULL" +
                ");",
            "CREATE TABLE IF NOT EXISTS masini (" +
                " id SERIAL PRIMARY KEY," +
                " make VARCHAR(50) NOT NULL," +
                " model VARCHAR(50) NOT NULL," +
                " year INT NOT NULL," +
                " daily_rate NUMERIC(10,2) NOT NULL," +
                " available BOOLEAN NOT NULL" +
                ");",
            "CREATE TABLE IF NOT EXISTS motociclete (" +
                " id SERIAL PRIMARY KEY," +
                " make VARCHAR(50) NOT NULL," +
                " model VARCHAR(50) NOT NULL," +
                " year INT NOT NULL," +
                " daily_rate NUMERIC(10,2) NOT NULL," +
                " available BOOLEAN NOT NULL" +
                ");",
            "CREATE TABLE IF NOT EXISTS biciclete (" +
                " id SERIAL PRIMARY KEY," +
                " make VARCHAR(50) NOT NULL," +
                " model VARCHAR(50) NOT NULL," +
                " year INT NOT NULL," +
                " daily_rate NUMERIC(10,2) NOT NULL," +
                " available BOOLEAN NOT NULL" +
                ");",
            "CREATE TABLE IF NOT EXISTS clienti (" +
                " cnp VARCHAR(13) PRIMARY KEY," +
                " nume VARCHAR(100) NOT NULL," +
                " email VARCHAR(100) NOT NULL," +
                " telefon VARCHAR(20) NOT NULL" +
                ");",
            "CREATE TABLE IF NOT EXISTS rezervari (" +
                " booking_id VARCHAR(50) PRIMARY KEY," +
                " client_cnp VARCHAR(13) NOT NULL," +
                " data_inceput DATE NOT NULL," +
                " data_sfarsit DATE NOT NULL," +
                " cost_total NUMERIC(10,2) NOT NULL" +
                ");",
            "CREATE TABLE IF NOT EXISTS client_history (" +
                " id SERIAL PRIMARY KEY," +
                " history_id VARCHAR(50)," +
                " client_cnp VARCHAR(13)," +
                " booking_id VARCHAR(50)," +
                " action_date VARCHAR(20)," +
                " action VARCHAR(100)" +
                ");"
        );

        Connection connection = Database.connect();
        if (connection == null) {
            System.out.println("Nu exista conexiune la PostgreSQL.");
            return;
        }

        try (Statement statement = connection.createStatement()) {
            for (String sql : statements) {
                statement.execute(sql);
            }
            System.out.println("Tabelele din pgAdmin au fost create/verificate cu succes.");
        } catch (SQLException exception) {
            System.out.println("Eroare la crearea tabelelor: " + exception.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException exception) {
                System.out.println("Eroare la inchiderea conexiunii: " + exception.getMessage());
            }
        }
    }

    private static void printPgAdminTables() {
        String sql = "SELECT table_name FROM information_schema.tables " +
                "WHERE table_schema = 'public' AND table_name IN (" +
                "'branch', 'staff', 'vehicle', 'masini', 'motociclete', 'biciclete', 'clienti', 'rezervari', 'client_history') " +
                "ORDER BY table_name";

        Connection connection = Database.connect();
        if (connection == null) {
            System.out.println("Nu exista conexiune la PostgreSQL.");
            return;
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Tabele existente in schema public:");
            while (resultSet.next()) {
                System.out.println("- " + resultSet.getString("table_name"));
            }
        } catch (SQLException exception) {
            System.out.println("Eroare la listarea tabelelor: " + exception.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException exception) {
                System.out.println("Eroare la inchiderea conexiunii: " + exception.getMessage());
            }
        }
    }
}