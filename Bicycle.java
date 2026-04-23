public class Bicycle extends Vehicle {
    private String bicycleType;
    private BicycleEquipment equipment;

    public Bicycle(String make, String model, int year, double dailyRate, boolean available, String bicycleType, BicycleEquipment equipment) {
        super(make, model, year, dailyRate, available);
        this.bicycleType = bicycleType;
        this.equipment = equipment;
    }

    public String getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType) {
        this.bicycleType = bicycleType;
    }

    public BicycleEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(BicycleEquipment equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", bicycleType='" + bicycleType + '\'' + ", equipment=" + equipment + '}');
    }
}
