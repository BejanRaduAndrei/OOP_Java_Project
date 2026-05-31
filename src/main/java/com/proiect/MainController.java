package com.proiect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    // fx:id-urile din Scene Builder
    @FXML private ListView<String> itemListView;
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;

    private ObservableList<String> listaProduse = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Legăm lista de date cu componenta grafică ListView
        itemListView.setItems(listaProduse);

        // Date de test inițiale
        listaProduse.addAll("Exemplu Produs 1", "Exemplu Produs 2");
    }

    // Metoda legată la "On Action" pe butonul Adauga
    @FXML
    private void handleFormSubmit() {
        String nume = nameField.getText();
        String descriere = descriptionField.getText();

        if (nume != null && !nume.trim().isEmpty()) {
            // Adăugăm direct în listă ca String pentru simplitate acum
            listaProduse.add(nume + " - " + descriere);

            // Ștergem textul din câmpuri
            nameField.clear();
            descriptionField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eroare Validare");
            alert.setHeaderText(null);
            alert.setContentText("Câmpul 'Nume' este obligatoriu!");
            alert.showAndWait();
        }
    }
}