public class Maintenance {
    private String maintenanceId;
    private Vehicle vehicle;
    private String maintenanceType;
    private String date;
    private double cost;
    private String description;

    public Maintenance(String maintenanceId, Vehicle vehicle, String maintenanceType, String date, double cost, String description) {
        this.maintenanceId = maintenanceId;
        this.vehicle = vehicle;
        this.maintenanceType = maintenanceType;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    public String getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(String maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenanceId='" + maintenanceId + '\'' +
                ", vehicle=" + vehicle +
                ", maintenanceType='" + maintenanceType + '\'' +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }
}