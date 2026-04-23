public class Motorcycle extends AutoVehicle {
    private boolean hasTopCase;

    public Motorcycle(String make, String model, int year, double dailyRate, boolean available, int engineCapacityCc, boolean hasTopCase) {
        super(make, model, year, dailyRate, available, engineCapacityCc);
        this.hasTopCase = hasTopCase;
    }

    public boolean isHasTopCase() {
        return hasTopCase;
    }

    public void setHasTopCase(boolean hasTopCase) {
        this.hasTopCase = hasTopCase;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", hasTopCase=" + hasTopCase + '}');
    }
}
