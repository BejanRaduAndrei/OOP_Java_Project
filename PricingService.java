import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PricingService {

    public double calculateRentalPrice(Vehicle vehicle, String startDate, String endDate) {
        if (vehicle == null || startDate == null || endDate == null) {
            return 0.0;
        }

        try {
            LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

            long daysRented = ChronoUnit.DAYS.between(start, end);
            if (daysRented < 0) {
                return 0.0;
            }

            double totalPrice = daysRented * vehicle.getDailyRate();
            return totalPrice;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public double calculateRentalPriceWithDiscount(Vehicle vehicle, String startDate, String endDate, double discountPercentage) {
        double totalPrice = calculateRentalPrice(vehicle, startDate, endDate);
        double discount = (totalPrice * discountPercentage) / 100;
        return totalPrice - discount;
    }

    public double calculateDailyPrice(Vehicle vehicle) {
        if (vehicle == null) {
            return 0.0;
        }
        return vehicle.getDailyRate();
    }
}
