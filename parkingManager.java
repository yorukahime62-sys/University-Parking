import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class parkingManager {
    private static final String DATA_FILE = "parking_data.ser";
    private List<ParkingSlot> parkingSlots;

    @SuppressWarnings("unchecked")
    public void loadOrInitializeSlots() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                parkingSlots = (List<ParkingSlot>) in.readObject();
                return;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Could not load saved data (" + e.getMessage() + "). Starting fresh.");
            }
        }
        initializeParkingSlots();
        saveParkingData();
    }

    public void saveParkingData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(parkingSlots);
        } catch (IOException e) {
            System.out.println("Error saving parking data: " + e.getMessage());
        }
    }

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
                slot.getVehicle().logVehicleDetails();
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
        String licensePlate = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (licensePlate.isEmpty()) {
            System.out.println("License plate cannot be empty.");
            return;
        }
        for (ParkingSlot slot : parkingSlots) {
            if (slot.isOccupied()
                    && slot.getVehicle().getLicensePlate().equalsIgnoreCase(licensePlate)) {
                System.out.println("Vehicle is already parked in slot number: " + slot.getSlotNumber());
                return;
            }
        }

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
                slot.occupySlot(vehicle);
                saveParkingData();
                vehicle.logVehicleDetails();
                System.out.println("Slot number: " + slot.getSlotNumber());
                return;
            }
        }

        System.out.println("No available slots for " + vehicleType);
    }

    public void unparkVehicle(Scanner scanner) {
        System.out.print("Enter slot number to unpark: ");
        int slotNumber;
        while (true) {
            if (!scanner.hasNextLine()) {
                return;
            }
            try {
                slotNumber = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid slot number. Please enter a number: ");
            }
        }

        for (ParkingSlot slot : parkingSlots) {
            if (slot.getSlotNumber() == slotNumber) {
                if (slot.isOccupied()) {
                    slot.freeSlot();
                    saveParkingData();
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