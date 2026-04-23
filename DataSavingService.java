import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataSavingService {
    private String filePath;

    public DataSavingService(String filePath) {
        this.filePath = filePath;
    }

    public void saveClients(List<Client> clients) {
        try (FileWriter writer = new FileWriter(filePath + "/clients.txt")) {
            writer.write("=== CLIENT DATA ===\n\n");
            for (Client client : clients) {
                writer.write(client.toString() + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving clients: " + e.getMessage());
        }
    }

    public void saveVehicles(List<Vehicle> vehicles) {
        try (FileWriter writer = new FileWriter(filePath + "/vehicles.txt")) {
            writer.write("=== VEHICLE DATA ===\n\n");
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.toString() + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving vehicles: " + e.getMessage());
        }
    }

    public void saveBookings(List<Booking> bookings) {
        try (FileWriter writer = new FileWriter(filePath + "/bookings.txt")) {
            writer.write("=== BOOKING DATA ===\n\n");
            for (Booking booking : bookings) {
                writer.write(booking.toString() + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    public void saveClientHistory(List<ClientHistory> histories) {
        try (FileWriter writer = new FileWriter(filePath + "/client_history.txt")) {
            writer.write("=== CLIENT HISTORY ===\n\n");
            for (ClientHistory history : histories) {
                writer.write(history.toString() + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving client history: " + e.getMessage());
        }
    }

    public void saveVehicleStatuses(List<VehicleStatus> statuses) {
        try (FileWriter writer = new FileWriter(filePath + "/vehicle_status.txt")) {
            writer.write("=== VEHICLE STATUS ===\n\n");
            for (VehicleStatus status : statuses) {
                writer.write(status.toString() + "\n");
            }
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving vehicle statuses: " + e.getMessage());
        }
    }

    public void saveAllData(List<Client> clients, List<Vehicle> vehicles, List<Booking> bookings, 
                           List<ClientHistory> histories, List<VehicleStatus> statuses) {
        try (FileWriter writer = new FileWriter(filePath + "/all_data.txt")) {
            writer.write("=== COMPLETE SYSTEM DATA ===\n");
            writer.write("Generated at: " + java.time.LocalDateTime.now() + "\n\n");

            writer.write("--- CLIENTS ---\n");
            for (Client client : clients) {
                writer.write(client.toString() + "\n");
            }

            writer.write("\n--- VEHICLES ---\n");
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.toString() + "\n");
            }

            writer.write("\n--- BOOKINGS ---\n");
            for (Booking booking : bookings) {
                writer.write(booking.toString() + "\n");
            }

            writer.write("\n--- CLIENT HISTORY ---\n");
            for (ClientHistory history : histories) {
                writer.write(history.toString() + "\n");
            }

            writer.write("\n--- VEHICLE STATUS ---\n");
            for (VehicleStatus status : statuses) {
                writer.write(status.toString() + "\n");
            }

            System.out.println("All data saved successfully to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving all data: " + e.getMessage());
        }
    }
}
