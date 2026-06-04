package com.proiect;

import java.util.List;
import java.util.Scanner;

public class TerminalMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final ClientService clientService = new ClientService();
    private final VehicleService vehicleService = new VehicleService();
    private final DataSavingService.ClientService clientRepo = new DataSavingService.ClientService();
    private final DataSavingService.CarService carRepo = new DataSavingService.CarService();

    public void start() {
        loadPersistedData();

        boolean running = true;
        while (running) {
            printMenu();
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> addClient();
                case "2" -> listClients();
                case "3" -> deleteClient();
                case "4" -> addCar();
                case "5" -> listCars();
                case "6" -> deleteCar();
                case "0" -> {
                    running = false;
                    System.out.println("Iesire din interfata terminal.");
                }
                default -> System.out.println("Optiune invalida.");
            }
        }
    }

    private void loadPersistedData() {
        try {
            List<Client> clients = clientRepo.readAll();
            for (Client client : clients) {
                clientService.addClient(client);
            }
        } catch (Exception e) {
            System.out.println("Nu am putut incarca clientii din baza de date: " + e.getMessage());
        }

        try {
            List<Car> cars = carRepo.readAll();
            for (Car car : cars) {
                vehicleService.addVehicle(car);
            }
        } catch (Exception e) {
            System.out.println("Nu am putut incarca vehiculele din baza de date: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== MENIU TERMINAL =====");
        System.out.println("1. Adauga client");
        System.out.println("2. Afiseaza clienti");
        System.out.println("3. Sterge client");
        System.out.println("4. Adauga masina");
        System.out.println("5. Afiseaza masini");
        System.out.println("6. Sterge masina");
        System.out.println("0. Iesire");
        System.out.print("Alege optiunea: ");
    }

    private void addClient() {
        try {
            System.out.print("Nume: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CNP: ");
            String cnp = scanner.nextLine();
            System.out.print("Telefon: ");
            String phone = scanner.nextLine();

            Client client = clientService.createClient(name, email, cnp, phone);
            if (client == null) {
                System.out.println("Clientul exista deja sau CNP-ul este invalid.");
                return;
            }

            clientRepo.create(client);
            System.out.println("Client adaugat cu succes.");
        } catch (InvalidRomanianPhoneNumberException ex) {
            System.out.println("Telefon invalid: " + ex.getMessage());
        }
    }

    private void listClients() {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Nu exista clienti.");
            return;
        }

        System.out.println("--- CLIENTI ---");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i));
        }
    }

    private void deleteClient() {
        System.out.print("Introdu CNP-ul clientului de sters: ");
        String cnp = scanner.nextLine();

        Client client = clientService.findClientByCnp(cnp);
        if (client == null) {
            System.out.println("Client inexistent.");
            return;
        }

        clientRepo.delete(cnp);
        clientService.removeClientByCnp(cnp);
        System.out.println("Client sters.");
    }

    private void addCar() {
        try {
            System.out.print("Marca: ");
            String make = scanner.nextLine();
            System.out.print("Model: ");
            String model = scanner.nextLine();
            System.out.print("An fabricatie: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Tarif zilnic: ");
            double rate = Double.parseDouble(scanner.nextLine().trim());

            Car car = vehicleService.createCar(make, model, year, rate);
            carRepo.create(car);
            System.out.println("Masina adaugata cu succes.");
        } catch (NumberFormatException ex) {
            System.out.println("Anul si tariful trebuie sa fie numerice.");
        }
    }

    private void listCars() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("Nu exista masini.");
            return;
        }

        System.out.println("--- MASINI ---");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }
    }

    private void deleteCar() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("Nu exista masini de sters.");
            return;
        }

        listCars();
        System.out.print("Alege numarul masinii de sters: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index < 0 || index >= vehicles.size()) {
                System.out.println("Index invalid.");
                return;
            }

            vehicles.remove(index);
            System.out.println("Masina a fost stearsa din lista curenta.");
            System.out.println("Nota: stergerea din DB nu este implementata pentru masina selectata.");
        } catch (NumberFormatException ex) {
            System.out.println("Introdu un numar valid.");
        }
    }
}