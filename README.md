# 🚗 Sistem Management Vehicule (Vehicle Rental System)

Proiect academic ce implementează un sistem complet pentru gestiunea unei flote de vehicule (mașini, motociclete, biciclete), a clienților și a fluxului operațional de rezervări. Aplicația respectă structura arhitecturală **MVC** (Model-View-Controller) și folosește tehnologii moderne pentru persistență și interfață grafică.

---

## 📋 1. Definirea Sistemului

### 👥 Cele 10 Tipuri de Obiecte (Entități) din Sistem
1. **`Vehicle`** – Clasă abstractă/bază ce încapsulează caracteristicile comune ale flotei.
2. **`AutoVehicle`** – Extinde `Vehicle`, adăugând proprietăți specifice vehiculelor cu motor.
3. **`Car`** – Entitate concretă pentru autoturisme (număr înmatriculare, combustibil, kilometraj).
4. **`Motorcycle`** – Entitate concretă pentru motociclete (include indicator pentru topcase).
5. **`Bicycle`** – Entitate concretă pentru biciclete, utilizând o clasă wrapper pentru echipamente.
6. **`BicycleEquipment`** – Obiect suport ce reține dotările de siguranță (cască, antifurt, lumini).
7. **`Client`** – Datele de identificare ale clienților firmei (indexat unic pe CNP).
8. **`Booking`** – Entitatea centrală care mapează o perioadă de închiriere între un client și un vehicul.
9. **`Invoice`** – Documentul fiscal generat automat la finalizarea sau crearea unei rezervări.
10. **`ClientHistory` / `ClientHistoryRecord`** – Istoricul de audit și trasabilitate al acțiunilor per client.

### ⚡ Cele 15 Acțiuni Distincte din Logica de Business
1. `ClientService.createClient` — Înregistrarea și validarea unui client nou.
2. `ClientService.readClient` — Căutarea unui client în baza de date după CNP.
3. `ClientService.updateClient` — Actualizarea datelor de contact ale clientului.
4. `ClientService.deleteClient` — Eliminarea unui client din evidența activă.
5. `VehicleService.createCar` — Instanțierea unui autoturism prin metode Factory.
6. `VehicleService.createMotorcycle` — Instanțierea și configurarea unei motociclete.
7. `VehicleService.createBicycle` — Adăugarea unei biciclete cu echipament specific în flotă.
8. `VehicleService.filterAvailable` — Filtrarea dinamică a vehiculelor libere pentru închiriere.
9. `VehicleService.changeAvailability` — Marcarea manuală/automată a statusului de disponibilitate.
10. `BookingService.createBooking` — Crearea unei rezervări cu blocarea automată a vehiculului.
11. `BookingService.listActiveBookings` — Listarea rezervărilor aflate în desfășurare.
12. `PricingService.calculateRentalPrice` — Calcularea tarifului brut pe baza perioadei de închiriere.
13. `PricingService.calculateDiscountedPrice` — Aplicarea algoritmilor de reducere pe baza istoricului.
14. `DataSavingService.saveAllData` — Sincronizarea manuală/automată a stării în baza de date.
15. `AuditService.logAction` — Înregistrarea automată a acțiunii curente în fișierul de audit.

---

## 💻 2. Implementare și Structura Programului

### 🛡️ Încapsulare și Constrângeri OOD
Toate modelele respectă cu strictețe principiul încapsulării: atributele sunt declarate `private`, iar accesul la starea internă este expus controlat prin metode de tip `getter` și `setter`. Validările critice (precum verificarea lungimii CNP-ului sau validarea numărului de telefon românesc) sunt tratate direct la nivel de constructor sau builder.

### 📚 Utilizarea Colecțiilor din Java Collections Framework
* **`List (ArrayList)`**: Utilizată în `VehicleService` pentru stocarea și indexarea colecțiilor secvențiale de vehicule.
* **`Set (HashSet)`**: Utilizată în `ClientService` pentru a garanta unicitatea clienților pe baza CNP-ului.
* **`Set (TreeSet - Sortat)`**: Utilizată în `ClientHistory`, ordonând înregistrările cronologic pe baza unui comparator personalizat pe timestamp.

### 🔗 Moștenire și Polimorfism
Moștenirea este folosită pentru ierarhizarea flotei de vehicule:
`Rentable (Interfață)` ➡️ `Vehicle (Clasă de Bază)` ➡️ `AutoVehicle` ➡️ `Car` / `Motorcycle`.  
Polimorfismul permite tratarea uniformă a flotei prin intermediul colecțiilor de tip `List<Vehicle>`, utilizând metodele polimorfice din interfața `Rentable` pentru calcularea estimativă a prețurilor.

### ⚠ Managementul Excepțiilor
Aplicația definește excepții custom pentru prevenirea stărilor inconsistente în sistem:
* **`InvalidRomanianPhoneNumberException`**: Aruncată în clasa `Client` dacă numărul de telefon introdus nu respectă formatul național (lungime, prefix).
* **`InvalidCnpException`**: Aruncată în momentul în care validarea structurală a CNP-ului eșuează în formularele de înregistrare.

---

## 📊 3. Persistență JDBC și Modelul de Date

Sistemul utilizează un vendor de baze de date SQL (**PostgreSQL**) accesat nativ prin intermediul **JDBC**. Arhitectura include servicii de tip Singleton Generic care elimină duplicarea codului SQL pentru operațiile CRUD din cele 6 repository-uri principale (`Client`, `Branch`, `Staff`, `Car`, `Motorcycle`, `Booking`).

### 🗺️ Diagrama Entity Relationship Diagram
<img width="1222" height="587" alt="Screenshot 2026-05-31 224130" src="https://github.com/user-attachments/assets/dde3d8bc-7177-41b2-8df5-125a35bb72af" />
