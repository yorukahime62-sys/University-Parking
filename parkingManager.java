import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class parkingManager{
    private List<ParkingSlot> parkingSlots;

    // 1 to 4 for motorcycles, 5 to 10 for cars
    public void initializeParkingSlots() {
        parkingSlots = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            parkingSlots.add(new ParkingSlot(i, "Motorcycle"));
        }

        for (int i = 5; i <= 10; i++) {
            parkingSlots.add(new ParkingSlot(i, "Car"));
        }
    }

    public void displayAvailableSlots() {
        System.out.println("Available Parking Slots:");
        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isOccupied()) {
                System.out.println("Slot Number: " + slot.getSlotNumber() + ", Vehicle Type: " + slot.getVehicleType());
            }
        }
    }

    public void diplayOccupiedSlots() {
        System.out.println("Occupied Parking Slots:");
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied()) {
                System.out.println("Slot Number: " + slot.getSlotNumber() + ", Vehicle Type: " + slot.getVehicleType());
            }
        }
    }

    public void parkVehicle(Scanner scanner) {
        System.out.print("Enter vehicle type (Motorcycle/Car): ");
        String inputType = scanner.nextLine().trim();

        String vehicleType;
        if (inputType.equalsIgnoreCase("Motorcycle") || inputType.equalsIgnoreCase("M")) {
            vehicleType = "Motorcycle";
        } else if (inputType.equalsIgnoreCase("Car") || inputType.equalsIgnoreCase("C")) {
            vehicleType = "Car";
        } else {
            System.out.println("Invalid vehicle type.");
            return;
        }

        System.out.print("Enter license plate: ");
        String licensePlate = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        Vehicle vehicle;
        if (vehicleType.equals("Motorcycle")) {
            vehicle = new Motorcycle(licensePlate, vehicleType, color, model);
        } else {
            vehicle = new car(licensePlate, vehicleType, color, model);
        }

        for (ParkingSlot slot : parkingSlots) {
            if (!slot.isOccupied() && slot.getVehicleType().equalsIgnoreCase(vehicleType)) {
                slot.occupySlot();
                vehicle.logVehicleDetails();
                System.out.println("Slot number: " + slot.getSlotNumber());
                return;
            }
        }
        
        System.out.println("No available slots for " + vehicleType);
    }

    public void unparkVehicle(Scanner scanner) {
        System.out.print("Enter slot number to unpark: ");
        int slotNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotNumber() == slotNumber) {
                if (slot.isOccupied()) {
                    slot.freeSlot();
                    System.out.println("Vehicle unparked from slot number: " + slotNumber);
                } else {
                    System.out.println("Slot number " + slotNumber + " is already free.");
                }
                return;
            }
        }

        System.out.println("Invalid slot number.");
    }
        
}
