public interface Rentable {
    double getDailyRate();
    boolean isAvailable();
    void setAvailable(boolean available);

    default double estimatePrice(int days) {
        if (days <= 0) {
            return 0.0;
        }
        return getDailyRate() * days;
    }
}
