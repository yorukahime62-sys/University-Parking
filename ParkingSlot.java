// This class represents a parking slot in the parking system.
// It contains attributes such as slot number, vehicle type, and occupancy status.

public class ParkingSlot{
    private int slotNumber;
    private String vehicleType;
    private boolean isOccupied;



    public ParkingSlot(int slotNumber, String vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

        public int getSlotNumber() {
        return slotNumber;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public boolean occupySlot() {
        return isOccupied = true;
    }
    public boolean freeSlot() {
        return isOccupied = false;
    }

}