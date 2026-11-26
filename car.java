public class car extends Vehicle {
    private static final long serialVersionUID = 1L;
    private String color;
    private String model;

    public car(String licensePlate, String vehicleType, String color, String model) {
        super(licensePlate, vehicleType);
        this.color = color;
        this.model = model;
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
    
    @Override
    public void logVehicleDetails() {
        System.out.println("Car Parked: License Plate - " + getLicensePlate() + ", Color - " + color + ", Model - " + model);
    }
}
