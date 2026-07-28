# Vehicle Rental System

Academic project implementing a complete system for managing a vehicle fleet (cars, motorcycles, bicycles), client records, and the operational booking workflow. The application adheres to the MVC (Model-View-Controller) architectural structure and uses modern technologies for data persistence and the graphical user interface.

---

## 1. System Definition

### The 10 Object Types (Entities) in the System
1. **`Vehicle`** – Abstract/base class encapsulating common characteristics across the fleet.
2. **`AutoVehicle`** – Extends `Vehicle`, adding properties specific to motorized vehicles.
3. **`Car`** – Concrete entity for automobiles (license plate number, fuel type, mileage).
4. **`Motorcycle`** – Concrete entity for motorcycles (includes a top case indicator).
5. **`Bicycle`** – Concrete entity for bicycles, utilizing a wrapper class for equipment.
6. **`BicycleEquipment`** – Support object storing safety equipment details (helmet, lock, lights).
7. **`Client`** – Identifying data for company clients (uniquely indexed by national identification number / CNP).
8. **`Booking`** – Central entity mapping a rental period between a client and a vehicle.
9. **`Invoice`** – Fiscal document automatically generated upon booking creation or completion.
10. **`ClientHistory` / `ClientHistoryRecord`** – Audit log and action traceability history per client.

### The 15 Distinct Actions in the Business Logic
1. `ClientService.createClient` — Registration and validation of a new client.
2. `ClientService.readClient` — Searching for a client in the database by national ID (CNP).
3. `ClientService.updateClient` — Updating client contact information.
4. `ClientService.deleteClient` — Removing a client from active records.
5. `VehicleService.createCar` — Instantiating a car via Factory methods.
6. `VehicleService.createMotorcycle` — Instantiating and configuring a motorcycle.
7. `VehicleService.createBicycle` — Adding a bicycle with specific safety equipment to the fleet.
8. `VehicleService.filterAvailable` — Dynamically filtering vehicles available for rent.
9. `VehicleService.changeAvailability` — Manually/automatically updating availability status.
10. `BookingService.createBooking` — Creating a booking with automatic vehicle reservation.
11. `BookingService.listActiveBookings` — Listing currently active bookings.
12. `PricingService.calculateRentalPrice` — Calculating base rental rates based on the rental duration.
13. `PricingService.calculateDiscountedPrice` — Applying discount algorithms based on client history.
14. `DataSavingService.saveAllData` — Manual/automatic synchronization of application state with the database.
15. `AuditService.logAction` — Automatically logging the current action to the audit file.

---

## 2. Implementation and Program Structure

### Encapsulation and OOD Constraints
All models strictly adhere to the principle of encapsulation: attributes are declared `private`, and access to internal state is exposed in a controlled manner via `getter` and `setter` methods. Critical validations (such as validating national identification number length or verifying Romanian phone number formats) are handled directly within constructors or builders.

### Use of Java Collections Framework
* **`List (ArrayList)`**: Used in `VehicleService` for storing and indexing sequential collections of vehicles.
* **`Set (HashSet)`**: Used in `ClientService` to guarantee client uniqueness based on national ID (CNP).
* **`Set (TreeSet - Sorted)`**: Used in `ClientHistory`, ordering records chronologically using a custom timestamp comparator.

### Inheritance and Polymorphism
Inheritance is used to construct the vehicle fleet hierarchy:  
`Rentable (Interface)` ➡️ `Vehicle (Base Class)` ➡️ `AutoVehicle` ➡️ `Car` / `Motorcycle`.  
Polymorphism allows uniform handling of the fleet through `List<Vehicle>` collections, utilizing polymorphic methods declared in the `Rentable` interface to compute rental price estimates.

### Exception Handling
The application defines custom exceptions to prevent inconsistent system states:
* **`InvalidRomanianPhoneNumberException`**: Thrown in the `Client` class if the entered phone number does not follow national formatting rules (length, prefix).
* **`InvalidCnpException`**: Thrown when structural validation of the national identification number fails during registration forms.

---

## 3. JDBC Persistence and Data Model

The system utilizes a SQL database vendor (PostgreSQL) accessed natively via JDBC. The architecture includes Generic Singleton services that eliminate SQL code duplication for CRUD operations across the 6 primary repositories (`Client`, `Branch`, `Staff`, `Car`, `Motorcycle`, `Booking`).

### Entity Relationship Diagram
<img width="1222" height="587" alt="Screenshot 2026-05-31 224130" src="https://github.com/user-attachments/assets/dde3d8bc-7177-41b2-8df5-125a35bb72af" />

## 4. Design Patterns and Coding Best Practices

The project implements widely recognized software design patterns to ensure loose coupling, ease of extensibility, and long-term maintainability.

### Implemented Design Patterns

#### Singleton Pattern
* **Purpose:** Ensures a single instance exists globally for efficient shared resource management.
* **Code Implementation:** The `AuditService` and `DataSavingService` classes utilize a public static `getInstance()` method.
* **Rationale:** Prevents race conditions when concurrently opening and writing to the `audit.csv` file or managing the JDBC connection pool.

#### Builder Pattern
* **Purpose:** Facilitates fluid, step-by-step construction of complex objects, eliminating telescoping constructors.
* **Code Implementation:** The `ClientBuilder` class enables instantiation of the `Client` entity through chained method calls such as `withName()`, `withEmail()`.
* **Rationale:** Centralizes structural integrity checks within the `.build()` method before the object becomes active in memory, improving code readability.

#### Factory Method Pattern
* **Purpose:** Centralizes and encapsulates instantiation logic for objects within the same class hierarchy, hiding concrete implementations from the client.
* **Code Implementation:** Dedicated methods within `VehicleService` (`createCar`, `createMotorcycle`, `createBicycle`).
* **Rationale:** Controllers or external classes can request new vehicle instances without depending structurally on concrete derived class constructors or their default parameters.

#### Observer Pattern (UI Data Binding)
* **Purpose:** Automatically notifies and updates View components whenever the data model state changes.
* **Code Implementation:** Reactive binding between `ObservableList` collections managed in `MainController` and graphical `ListView` components defined in FXML.
* **Rationale:** Guarantees strict adherence to the MVC architecture; adding or removing an item from a collection is instantly reflected on screen without needing to manually reload the entire interface.
