public class Motorcycle extends Vehicle {
     private static final long serialVersionUID = 1L;


    public Motorcycle(String licensePlate, String vehicleType, String color, String model) {
        super(licensePlate, vehicleType, color, model);
    }


@Override
    public void logVehicleDetails() {
        System.out.println("Motorcycle Parked: License Plate - " + getLicensePlate()
                + ", Color - " + getColor() + ", Model - " + getModel());
    }
}
