public class car extends Vehicle {
    private static final long serialVersionUID = 1L;


    public car(String licensePlate, String vehicleType, String color, String model) {
        super(licensePlate, vehicleType, color, model);
    }
    
@Override
    public void logVehicleDetails() {
        System.out.println("Car Parked: License Plate - " + getLicensePlate()
                + ", Color - " + getColor() + ", Model - " + getModel());
    }
}
