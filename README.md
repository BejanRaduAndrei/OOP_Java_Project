**Proiect: Sistem simplu de închiriere vehicule**

Acest proiect este un demo minimal pentru gestionarea unei flote de vehicule (mașini, motociclete, biciclete), clienți și rezervări. Codul conține modele (entități), servicii care efectuează acțiuni și câteva utilitare pentru prețuri și salvare date.

**Structură generală & roluri**
- **Modele / Entități**: clase simple care rețin date despre obiecte (ex: `Vehicle`, `Client`, `Booking`).
- **Servicii**: clase care implementează logica de business și acțiuni (ex: `VehicleService`, `BookingService`, `ClientService`, `PricingService`, `DataSavingService`).
- **Utilitare / domain objects**: `Invoice`, `RentalAgreement`, `Maintenance`, `VehicleStatus`, `ClientHistory`.
- **`Main`**: demo care construiește obiecte, crează rezervări și arată funcționalități.

**Clase principale și moștenire (scurt)**
- `Rentable` (interfață)
  - Metode: `getDailyRate()`, `isAvailable()`, `setAvailable(boolean)`
  - Default: `estimatePrice(int days)` — oferă calcul estimat pe zile.

- `Vehicle` implements `Rentable`
  - Atribute: `make`, `model`, `year`, `dailyRate`, `available`.
  - Reprezintă tipul comun pentru toate vehiculele.

- `AutoVehicle` extends `Vehicle`
  - Atribut: `engineCapacityCc`.
  - Bază pentru vehicule cu motor.

- `Car` extends `AutoVehicle`
  - Atribute specifice: `licensePlate`, `fuelType`, `mileageKm`.

- `Motorcycle` extends `AutoVehicle`
  - Atribut specific: `hasTopCase`.

- `Bicycle` extends `Vehicle`
  - Atribute: `bicycleType`, `BicycleEquipment equipment`.
- `BicycleEquipment` — wrapper pentru dotări (casca, lanț/încuietoare, lumini, coș).

Alte clase de model / utilitare:
- `Client` — date client, include validare număr de telefon (aruncă `InvalidRomanianPhoneNumberException` dacă e invalid).
- `Booking` — rezervare între un `Client` și un `Vehicle` (date de început/sfârșit).
- `BookingService` — creează rezervări, verifică disponibilitate prin `VehicleService` și marchează vehiculul ca indisponibil.
- `VehicleService` — gestionează lista de vehicule, factory-uri helpers (`createCar`, `createMotorcycle`, `createBicycle`), filtre (toate, disponibile, pe tipuri) și marcaje de disponibilitate.
- `ClientService` — înregistrare și căutare clienți (index pe `cnp` pentru unicitate).
- `PricingService` — calculează prețul total între două date și aplică eventuale discount-uri.
- `DataSavingService` — salvează colecții de date în fișiere text (clienți, vehicule, rezervări etc.).
- `ClientHistory` — păstrează acțiunile istorice ale clienților; conține și clasa `ClientHistoryRecord`.
- `Maintenance`, `VehicleStatus`, `Invoice`, `RentalAgreement`, `Staff`, `Branch` — obiecte suport pentru funcționalități operaționale și raportare.

**Acțiuni importante implementate**
- Creare / înregistrare clienți (`ClientService.createClient`).
- Adăugare vehicule și interogare pe tipuri (`VehicleService.createCar` / `getAllCars` etc.).
- Creare rezervare (`BookingService.createBooking`)  verifică disponibilitatea și blochează vehiculul.
- Calcul preț închiriere (`PricingService.calculateRentalPrice` și `calculateRentalPriceWithDiscount`).
- Salvare date în fișiere (`DataSavingService.saveAllData` [in lucru]).
- Validare telefon românesc în `Client` (aruncare `InvalidRomanianPhoneNumberException`).


