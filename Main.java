import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        VehicleService vehicleService = new VehicleService();
        BookingService bookingService = new BookingService(vehicleService);
        PricingService pricingService = new PricingService();

        System.out.println("=== DEMO RENTAL SYSTEM ===");

        Client validClient = clientService.createClient(
                "Andrei Popescu",
                "andrei.popescu@email.com",
                "1960525123456",
                "+40721234567"
        );

        try {
            clientService.createClient(
                    "Client Invalid",
                    "invalid@email.com",
                    "1980101123456",
                    "12345"
            );
        } catch (InvalidRomanianPhoneNumberException exception) {
            System.out.println("Exceptie custom capturata: " + exception.getMessage());
        }

        Car car = vehicleService.createCar(
                "Skoda",
                "Octavia",
                2021,
                230.0,
                1598,
                "B-22-ABC",
                "diesel",
                68000
        );

        Motorcycle motorcycle = vehicleService.createMotorcycle(
                "Yamaha",
                "Tracer 7",
                2023,
                260.0,
                689,
                true
        );

        BicycleEquipment bicycleEquipment = new BicycleEquipment(true, true, true, false);
        Bicycle bicycle = vehicleService.createBicycle(
                "Cube",
                "Touring",
                2024,
                95.0,
                "city",
                bicycleEquipment
        );

        System.out.println("\nVehicule in colectie polimorfica:");
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        for (Vehicle vehicle : allVehicles) {
            System.out.println(vehicle);
        }

        System.out.println("\nFiltrare pe tipuri:");
        System.out.println("Masini: " + vehicleService.getAllCars().size());
        System.out.println("Motociclete: " + vehicleService.getAllMotorcycles().size());
        System.out.println("Biciclete: " + vehicleService.getAllBicycles().size());
        System.out.println("Model motocicleta demo: " + motorcycle.getModel());

        if (validClient != null) {
            Booking booking = bookingService.createBooking(
                    "BK-2026-001",
                    validClient,
                    car,
                    "2026-05-01",
                    "2026-05-05"
            );

            if (booking != null) {
                double total = pricingService.calculateRentalPrice(
                        booking.getVehicle(),
                        booking.getStartDate(),
                        booking.getEndDate()
                );
                System.out.println("\nBooking creat: " + booking);
                System.out.println("Cost total inchiriere: " + total + " RON");
            }
        }

        Rentable rentableVehicle = bicycle;
        System.out.println("\nPret estimat 3 zile pentru bicicleta: " + rentableVehicle.estimatePrice(3) + " RON");
    }
}
