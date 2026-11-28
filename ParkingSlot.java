import java.io.Serializable;



public class ParkingSlot implements Serializable {
    private static final long serialVersionUID = 1L;
    private int slotNumber;
    private String vehicleType;
    private Vehicle vehicle;


    //Constructor for Slot
    public ParkingSlot(int slotNumber, String vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
    }

    //getter setter meth
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

    //checker slot if occupied
    public boolean occupySlot(Vehicle vehicle) {
        if (isOccupied() || vehicle == null
                || !vehicleType.equalsIgnoreCase(vehicle.getVehicleType())) {
            return false;
        }
        this.vehicle = vehicle;
        return true;
    }

    //clears slot
    public void freeSlot() {
        this.vehicle = null;
    }

}
