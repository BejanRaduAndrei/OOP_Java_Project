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
- Creare rezervare (`BookingService.createBooking`) — verifică disponibilitatea și blochează vehiculul.
- Calcul preț închiriere (`PricingService.calculateRentalPrice` și `calculateRentalPriceWithDiscount`).
- Salvare date în fișiere (`DataSavingService.saveAllData` etc.).
- Validare telefon românesc în `Client` (aruncare `InvalidRomanianPhoneNumberException`).

**Flux demo din `Main`**
- Se creează instanțe de servicii: `ClientService`, `VehicleService`, `BookingService`, `PricingService`.
- Se înregistrează un client valid și se încearcă un client invalid pentru a demonstra excepția.
- Se creează exemple de `Car`, `Motorcycle`, `Bicycle` și se afișează colecția polimorfică.
- Se face o rezervare, se calculează costul și se afișează rezultatele.

**Observații & posibile îmbunătățiri**
- Persistență: în loc de fișiere text, se poate folosi o bază de date (JDBC / ORM).
- Validări mai robuste (formate de date, intervale, existența vehiculului în serviciu înainte de rezervare).
- Tranzacții: operațiile care schimbă starea (ex: rezervare + marcarea vehiculului) ar putea fi atomice.
- Testare: adăugarea unui set de teste unitare pentru servicii.

Dacă vrei, pot adăuga exemple de utilizare (snippet-uri de cod) în README sau pot genera un fișier `CONTRIBUTING.md` cu pași pentru rulare și testare.
