import java.io.Serializable;


public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String licensePlate;
    private String vehicleType;

    public Vehicle(String licensePlate, String vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void logVehicleDetails() {
        System.out.println("Vehicle Parked");
    }

}
