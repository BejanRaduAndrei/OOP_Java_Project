public class VehicleStatus {
    private Vehicle vehicle;
    private String status;
    private String lastMaintenanceDate;
    private int maintenanceCount;

    public VehicleStatus(Vehicle vehicle, String status, String lastMaintenanceDate, int maintenanceCount) {
        this.vehicle = vehicle;
        this.status = status;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.maintenanceCount = maintenanceCount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(String lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public int getMaintenanceCount() {
        return maintenanceCount;
    }

    public void setMaintenanceCount(int maintenanceCount) {
        this.maintenanceCount = maintenanceCount;
    }

    @Override
    public String toString() {
        return "VehicleStatus{" +
                "vehicle=" + vehicle +
                ", status='" + status + '\'' +
                ", lastMaintenanceDate='" + lastMaintenanceDate + '\'' +
                ", maintenanceCount=" + maintenanceCount +
                '}';
    }
}
