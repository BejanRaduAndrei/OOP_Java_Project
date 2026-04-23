public class Car extends AutoVehicle {
    private String licensePlate;
    private String fuelType;
    private int mileageKm;

    public Car(String make, String model, int year, double dailyRate, boolean available, int engineCapacityCc, String licensePlate, String fuelType, int mileageKm) {
        super(make, model, year, dailyRate, available, engineCapacityCc);
        this.licensePlate = licensePlate;
        this.fuelType = fuelType;
        this.mileageKm = mileageKm;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(int mileageKm) {
        this.mileageKm = mileageKm;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", licensePlate='" + licensePlate + '\'' + ", fuelType='" + fuelType + '\'' + ", mileageKm=" + mileageKm + '}');
    }
}
