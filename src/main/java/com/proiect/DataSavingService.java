package com.proiect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataSavingService {

    private static DataSavingService instance;

    private DataSavingService() {}

    public static DataSavingService getInstance() {
        if (instance == null) {
            instance = new DataSavingService(); 
        }
        return instance;
    }

    public static class ClientService implements GenericRepository<Client, String> {
        
        @Override
        public void create(Client client) {
            String sql = "INSERT INTO clienti (cnp, nume, email, telefon) VALUES (?, ?, ?, ?)";
            
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, client.getCnp());
                pstmt.setString(2, client.getName());
                pstmt.setString(3, client.getEmail());
                pstmt.setString(4, client.getPhoneNumber());
                
                pstmt.executeUpdate(); 
                System.out.println("Client adăugat cu succes: " + client.getName());
                AuditService.getInstance().logAction("create_client_db");
            } catch (SQLException e) {
                System.out.println("Eroare la adăugare client: " + e.getMessage());
            }
        }

        @Override
        public Client read(String cnp) {
            String sql = "SELECT * FROM clienti WHERE cnp = ?";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, cnp);
                ResultSet rs = pstmt.executeQuery(); 
                
                if (rs.next()) { 
                    return new Client(
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getString("cnp"),
                        rs.getString("telefon")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Eroare la citire client: " + e.getMessage());
            }
            return null; 
        }

        @Override
        public List<Client> readAll() {
            List<Client> lista = new ArrayList<>();
            String sql = "SELECT * FROM clienti";
            try (Connection conn = Database.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                while (rs.next()) {
                    Client c = new Client(
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getString("cnp"),
                        rs.getString("telefon")
                    );
                    lista.add(c);
                }
            } catch (SQLException e) {
                System.out.println("Eroare la citire listă clienți: " + e.getMessage());
            }
            return lista;
        }

        @Override
        public void update(Client client) {
            String sql = "UPDATE clienti SET nume = ?, email = ?, telefon = ? WHERE cnp = ?";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, client.getName());
                pstmt.setString(2, client.getEmail());
                pstmt.setString(3, client.getPhoneNumber());
                pstmt.setString(4, client.getCnp());
                
                pstmt.executeUpdate();
                System.out.println("Client modificat cu succes!");
                AuditService.getInstance().logAction("update_client_db");
            } catch (SQLException e) {
                System.out.println("Eroare la modificare client: " + e.getMessage());
            }
        }

        @Override
        public void delete(String cnp) {
            String sql = "DELETE FROM clienti WHERE cnp = ?";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, cnp);
                pstmt.executeUpdate();
                System.out.println("Client șters din baza de date.");
                AuditService.getInstance().logAction("delete_client_db");
            } catch (SQLException e) {
                System.out.println("Eroare la ștergere client: " + e.getMessage());
            }
        }
    }

    public static class BranchService implements GenericRepository<Branch, Integer> {
        @Override
        public void create(Branch b) {
            String sql = "INSERT INTO branch (nume, adresa) VALUES (?, ?)";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, b.getName());
                pstmt.setString(2, b.getAddress());
                pstmt.executeUpdate();
                System.out.println("Sucursală salvată: " + b.getName());
                AuditService.getInstance().logAction("create_branch_db");
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }

        @Override
        public Branch read(Integer id) {
            String sql = "SELECT * FROM branch WHERE id = ?";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Branch(rs.getString("nume"), rs.getString("adresa"), "Bucuresti");
                }
            } catch (SQLException e) { System.out.println(e.getMessage()); }
            return null;
        }

        @Override
        public List<Branch> readAll() { return new ArrayList<>(); }
        @Override
        public void update(Branch b) {}
        @Override
        public void delete(Integer id) {}
    }

    public static class StaffService implements GenericRepository<Staff, Integer> {
        @Override
        public void create(Staff s) {
            String sql = "INSERT INTO staff (nume, rol) VALUES (?, ?)";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, s.getName());
                pstmt.setString(2, s.getRole());
                pstmt.executeUpdate();
                System.out.println("Angajat adăugat: " + s.getName());
                AuditService.getInstance().logAction("create_staff_db");
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }

        @Override
        public Staff read(Integer id) { return null; }
        @Override
        public List<Staff> readAll() { return new ArrayList<>(); }
        @Override
        public void update(Staff s) {}
        @Override
        public void delete(Integer id) {}
    }

    public static class CarService implements GenericRepository<Car, Integer> {
        @Override
        public void create(Car car) {
            String sql = "INSERT INTO vehicule (marca, model, an_fabricatie, tarif_zilnic, disponibil, tip_vehicul) VALUES (?, ?, ?, ?, ?, 'CAR')";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, car.getMake());
                pstmt.setString(2, car.getModel());
                pstmt.setInt(3, car.getYear());
                pstmt.setDouble(4, car.getDailyRate());
                pstmt.setBoolean(5, car.isAvailable());
                pstmt.executeUpdate();
                System.out.println("Mașină salvată în baza de date!");
                AuditService.getInstance().logAction("create_car_db");
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }

        @Override
        public Car read(Integer id) {
            String sql = "SELECT * FROM vehicule WHERE id = ?";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Car(
                        rs.getString("marca"), rs.getString("model"), rs.getInt("an_fabricatie"),
                        rs.getDouble("tarif_zilnic"), rs.getBoolean("disponibil"), 1600, "B-01-ABC", "Gasoline", 50000
                    );
                }
            } catch (SQLException e) { System.out.println(e.getMessage()); }
            return null;
        }

        @Override
        public List<Car> readAll() { return new ArrayList<>(); }
        @Override
        public void update(Car car) {}
        @Override
        public void delete(Integer id) {}
    }

    public static class MotorcycleService implements GenericRepository<Motorcycle, Integer> {
        @Override
        public void create(Motorcycle moto) {
            String sql = "INSERT INTO vehicule (marca, model, an_fabricatie, tarif_zilnic, disponibil, tip_vehicul) VALUES (?, ?, ?, ?, ?, 'MOTORCYCLE')";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, moto.getMake());
                pstmt.setString(2, moto.getModel());
                pstmt.setInt(3, moto.getYear());
                pstmt.setDouble(4, moto.getDailyRate());
                pstmt.setBoolean(5, moto.isAvailable());
                pstmt.executeUpdate();
                System.out.println("Motocicletă salvată!");
                AuditService.getInstance().logAction("create_motorcycle_db");
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }

        @Override
        public Motorcycle read(Integer id) { return null; }
        @Override
        public List<Motorcycle> readAll() { return new ArrayList<>(); }
        @Override
        public void update(Motorcycle m) {}
        @Override
        public void delete(Integer id) {}
    }


    public static class BookingServiceRepository implements GenericRepository<Booking, String> {
        @Override
        public void create(Booking b) {
            String sql = "INSERT INTO rezervari (booking_id, client_cnp, data_inceput, data_sfarsit, cost_total) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = Database.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, b.getBookingId());
                pstmt.setString(2, b.getClient().getCnp());
                pstmt.setDate(3, Date.valueOf(b.getStartDate()));
                pstmt.setDate(4, Date.valueOf(b.getEndDate()));
                pstmt.setDouble(5, 920.0); 
                
                pstmt.executeUpdate();
                System.out.println("Rezervare înregistrată persistent: " + b.getBookingId());
                AuditService.getInstance().logAction("create_booking_db");
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }

        @Override
        public Booking read(String id) { return null; }
        @Override
        public List<Booking> readAll() { return new ArrayList<>(); }
        @Override
        public void update(Booking b) {}
        @Override
        public void delete(String id) {}
    }
}