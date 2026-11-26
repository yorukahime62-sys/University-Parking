import java.io.Serializable;


public abstract class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    private String licensePlate;
    private String vehicleType;
    private String color;
    private String model;

public Vehicle(String licensePlate, String vehicleType, String color, String model) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.color = color;
        this.model = model;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public abstract void logVehicleDetails();

    // public void logVehicleDetails() {
    //     System.out.println("Vehicle Parked");
    // }

}
