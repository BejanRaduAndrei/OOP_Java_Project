package com.proiect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {

    // Left sample list (kept for backward compatibility)
    @FXML private ListView<String> itemListView;

    // Client form controls
    @FXML private TextField clientNameField;
    @FXML private TextField clientEmailField;
    @FXML private TextField clientCnpField;
    @FXML private TextField clientPhoneField;
    @FXML private ListView<String> clientListView;

    // Vehicle form controls
    @FXML private TextField vehicleMakeField;
    @FXML private TextField vehicleModelField;
    @FXML private TextField vehicleYearField;
    @FXML private TextField vehicleRateField;
    @FXML private ListView<String> vehicleListView;

    private final ObservableList<String> listaProduse = FXCollections.observableArrayList();
    private final ObservableList<String> clients = FXCollections.observableArrayList();
    private final ObservableList<String> vehicles = FXCollections.observableArrayList();

    private final ClientService clientService = new ClientService();
    private final VehicleService vehicleService = new VehicleService();
    private final DataSavingService.ClientService clientRepo = new DataSavingService.ClientService();
    private final DataSavingService.CarService carRepo = new DataSavingService.CarService();

    @FXML
    public void initialize() {
        itemListView.setItems(listaProduse);
        clientListView.setItems(clients);
        vehicleListView.setItems(vehicles);

        listaProduse.addAll("Sistem pornit...", "Conexiune DB OK.");
    }

    @FXML
    private void handleAddClient() {
        String name = clientNameField.getText();
        String email = clientEmailField.getText();
        String cnp = clientCnpField.getText();
        String phone = clientPhoneField.getText();

        if (name == null || name.trim().isEmpty()) {
            showWarning("Nume invalid", "Câmpul 'Nume' este obligatoriu.");
            return;
        }

        try {
            // Use ClientService to keep in-memory list and DataSavingService to persist
            Client client = clientService.createClient(name, email, cnp, phone);
            if (client != null) {
                clients.add(client.getName() + " (" + client.getCnp() + ")");
                clientRepo.create(client); // persist
                clearClientForm();
            } else {
                showWarning("Eroare creare", "Clientul nu a putut fi creat (posibil CNP duplicat).");
            }
        } catch (InvalidRomanianPhoneNumberException ex) {
            showWarning("Telefon invalid", ex.getMessage());
        }
    }
 @FXML
    private void handleDeleteClient() {
        int selectedIndex = clientListView.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
            if (selectedIndex < clientService.getAllClients().size()) {
                Client clientDeSters = clientService.getAllClients().get(selectedIndex);
                
                // 1. Ștergem din baza de date folosind CNP-ul (String), nu obiectul complet
                try {
                    clientRepo.delete(clientDeSters.getCnp()); 
                } catch (Exception e) {
                    System.out.println("Eroare la ștergerea din DB: " + e.getMessage());
                }
                
                clientService.getAllClients().remove(selectedIndex);
            }
            
            clients.remove(selectedIndex);
            AuditService.getInstance().logAction("delete_client_ui");
        } else {
            showWarning("Selecție invalidă", "Vă rugăm să selectați un client din listă pentru a-l șterge.");
        }
    }

    @FXML
    private void handleDeleteVehicle() {
        int selectedIndex = vehicleListView.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
            if (selectedIndex < vehicleService.getAllVehicles().size()) {
                Vehicle vehiculDeSters = vehicleService.getAllVehicles().get(selectedIndex);
                
                // 2. Ștergem din baza de date folosind ID-ul (Integer), nu obiectul complet
                try {

                } catch (Exception e) {
                    System.out.println("Eroare la ștergerea din DB: " + e.getMessage());
                }
                
                vehicleService.getAllVehicles().remove(selectedIndex);
            }
            
            vehicles.remove(selectedIndex);
            AuditService.getInstance().logAction("delete_vehicle_ui");
        } else {
            showWarning("Selecție invalidă", "Vă rugăm să selectați un vehicul din listă pentru a-l șterge.");
        }
    }
    @FXML
    private void handleAddVehicle() {
        String make = vehicleMakeField.getText();
        String model = vehicleModelField.getText();
        String yearText = vehicleYearField.getText();
        String rateText = vehicleRateField.getText();

        if (make == null || make.trim().isEmpty()) {
            showWarning("Marca invalidă", "Câmpul 'Marca' este obligatoriu.");
            return;
        }

        int year = 0;
        double rate = 0.0;
        try {
            year = Integer.parseInt(yearText);
        } catch (Exception e) {
            showWarning("An invalid", "Introduceți un an corect.");
            return;
        }
        try {
            rate = Double.parseDouble(rateText);
        } catch (Exception e) {
            showWarning("Tarif invalid", "Introduceți un tarif numeric.");
            return;
        }

        Car car = vehicleService.createCar(make, model, year, rate);
        vehicles.add(car.getMake() + " " + car.getModel() + " (" + car.getYear() + ")");
        // attempt to persist (may print DB errors if DB not configured)
        carRepo.create(car);
        clearVehicleForm();
    }

    private void clearClientForm() {
        clientNameField.clear();
        clientEmailField.clear();
        clientCnpField.clear();
        clientPhoneField.clear();
    }

    private void clearVehicleForm() {
        vehicleMakeField.clear();
        vehicleModelField.clear();
        vehicleYearField.clear();
        vehicleRateField.clear();
    }

    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}