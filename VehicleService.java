import java.util.ArrayList;
import java.util.List;

public class VehicleService {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle createVehicle(String make, String model, int year, double dailyRate) {
        Vehicle vehicle = new Vehicle(make, model, year, dailyRate, true);
        vehicles.add(vehicle);
        return vehicle;
    }

    public Car createCar(String make, String model, int year, double dailyRate, int engineCapacityCc, String licensePlate, String fuelType, int mileageKm) {
        Car car = new Car(make, model, year, dailyRate, true, engineCapacityCc, licensePlate, fuelType, mileageKm);
        vehicles.add(car);
        return car;
    }

    public Car createCar(String make, String model, int year, double dailyRate) {
        Car car = new Car(make, model, year, dailyRate, true, 1400, "NEINREGISTRATA", "benzina", 0);
        vehicles.add(car);
        return car;
    }

    public Motorcycle createMotorcycle(String make, String model, int year, double dailyRate, int engineCapacityCc, boolean hasTopCase) {
        Motorcycle motorcycle = new Motorcycle(make, model, year, dailyRate, true, engineCapacityCc, hasTopCase);
        vehicles.add(motorcycle);
        return motorcycle;
    }

    public Bicycle createBicycle(String make, String model, int year, double dailyRate, String bicycleType, BicycleEquipment equipment) {
        Bicycle bicycle = new Bicycle(make, model, year, dailyRate, true, bicycleType, equipment);
        vehicles.add(bicycle);
        return bicycle;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car car) {
                cars.add(car);
            }
        }
        return cars;
    }

    public List<Motorcycle> getAllMotorcycles() {
        List<Motorcycle> motorcycles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Motorcycle motorcycle) {
                motorcycles.add(motorcycle);
            }
        }
        return motorcycles;
    }

    public List<Bicycle> getAllBicycles() {
        List<Bicycle> bicycles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Bicycle bicycle) {
                bicycles.add(bicycle);
            }
        }
        return bicycles;
    }

    public boolean isVehicleAvailable(Vehicle vehicle) {
        return vehicle != null && vehicle.isAvailable();
    }

    public void markAsUnavailable(Vehicle vehicle) {
        if (vehicle != null) {
            vehicle.setAvailable(false);
        }
    }

    public void markAsAvailable(Vehicle vehicle) {
        if (vehicle != null) {
            vehicle.setAvailable(true);
        }
    }
}