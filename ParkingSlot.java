import java.io.Serializable;



public class ParkingSlot implements Serializable {
    private static final long serialVersionUID = 1L;
    private int slotNumber;
    private String vehicleType;
    private Vehicle vehicle;



    public ParkingSlot(int slotNumber, String vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
    }

        public int getSlotNumber() {
        return slotNumber;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public boolean isOccupied() {
        return vehicle != null;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public boolean occupySlot(Vehicle vehicle) {
        if (isOccupied() || vehicle == null
                || !vehicleType.equalsIgnoreCase(vehicle.getVehicleType())) {
            return false;
        }
        this.vehicle = vehicle;
        return true;
    }
    public boolean freeSlot() {
        this.vehicle = null;
        return false;
    }

}
