public class AutoVehicle extends Vehicle {
    private int engineCapacityCc;

    public AutoVehicle(String make, String model, int year, double dailyRate, boolean available, int engineCapacityCc) {
        super(make, model, year, dailyRate, available);
        this.engineCapacityCc = engineCapacityCc;
    }

    public int getEngineCapacityCc() {
        return engineCapacityCc;
    }

    public void setEngineCapacityCc(int engineCapacityCc) {
        this.engineCapacityCc = engineCapacityCc;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", engineCapacityCc=" + engineCapacityCc + '}');
    }
}
