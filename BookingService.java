import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private final List<Booking> bookings = new ArrayList<>();
    private final VehicleService vehicleService;

    public BookingService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public Booking createBooking(String bookingId, Client client, Vehicle vehicle, String startDate, String endDate) {
        if (client == null || vehicle == null) {
            return null;
        }

        if (!vehicleService.isVehicleAvailable(vehicle)) {
            return null;
        }

        Booking booking = new Booking(bookingId, client, vehicle, startDate, endDate);
        bookings.add(booking);
        vehicleService.markAsUnavailable(vehicle);
        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public List<Booking> getActiveOrFutureBookings() {
        List<Booking> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Booking booking : bookings) {
            LocalDate startDate = parseDate(booking.getStartDate());
            LocalDate endDate = parseDate(booking.getEndDate());

            if (startDate == null || endDate == null) {
                continue;
            }

            boolean active = !today.isBefore(startDate) && !today.isAfter(endDate);
            boolean future = today.isBefore(startDate);

            if (active || future) {
                result.add(booking);
            }
        }

        return result;
    }

    public void displayActiveOrFutureBookings() {
        for (Booking booking : getActiveOrFutureBookings()) {
            System.out.println(booking);
        }
    }

    private LocalDate parseDate(String dateText) {
        try {
            return LocalDate.parse(dateText, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception exception) {
            return null;
        }
    }
}