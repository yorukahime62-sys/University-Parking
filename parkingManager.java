import java.io.File;                        //file path
import java.io.FileInputStream;             //Read
import java.io.FileOutputStream;            //Write
import java.io.IOException;                 //handle errors
import java.io.ObjectInputStream;           //deserialize
import java.io.ObjectOutputStream;          //serialize
import java.util.ArrayList;                 //resizable list
import java.util.List;                      //list
import java.util.Locale;                    //uppercase converstion
import java.util.Scanner;                   //Scan


public class parkingManager {
    private static final String DATA_FILE = "parking_data.ser"; //filename
    private List<ParkingSlot> parkingSlots;                     //collection slot

    @SuppressWarnings("unchecked")                              //remove warning from printing
    public void loadOrInitializeSlots() {
        File file = new File(DATA_FILE);                        //file reference
        if (file.exists()) {                                    //check if file exist
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {     //opens file to read
                parkingSlots = (List<ParkingSlot>) in.readObject(); //reads from file
                return;
            } catch (IOException | ClassNotFoundException e) {      //prevents program crash
                System.out.println("Could not load saved data (" + e.getMessage() + "). Starting fresh.");
            }
        }
        initializeParkingSlots(); //when no file loaded, builds default slot
        saveParkingData();        //saves to serialization file after creation of slots
    }

    public void saveParkingData() { //writes to the file serialization
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
            if (!slot.isOccupied()) {   //only prints slot not occupied
                System.out.println("Slot Number: " + slot.getSlotNumber() + ", Vehicle Type: " + slot.getVehicleType());
            }
        }
    }

    public void diplayOccupiedSlots() {
        System.out.println("Occupied Parking Slots:");
        for (ParkingSlot slot : parkingSlots) { //checks every slot
            if (slot.isOccupied()) {        //only those occupied
                System.out.println("Slot Number: " + slot.getSlotNumber() + ", Vehicle Type: " + slot.getVehicleType());
                slot.getVehicle().logVehicleDetails(); //calls polymorphic
            }
        }
    }

    public void parkVehicle(Scanner scanner) {
        System.out.print("Enter vehicle type (Motorcycle/Car): ");
        String inputType = scanner.nextLine().trim();   //reads then trims space surroumding

        String vehicleType; //holding vehicle type
        if (inputType.equalsIgnoreCase("Motorcycle") || inputType.equalsIgnoreCase("M")) {
            vehicleType = "Motorcycle";
        } else if (inputType.equalsIgnoreCase("Car") || inputType.equalsIgnoreCase("C")) {
            vehicleType = "Car";
        } else {
            System.out.println("Invalid vehicle type.");
            return;
        }

        System.out.print("Enter license plate: ");
        String licensePlate = scanner.nextLine().trim().toUpperCase(Locale.ROOT); //trims whitespce then CAPS
        if (licensePlate.isEmpty()) {
            System.out.println("License plate cannot be empty.");
            return;
        }
        for (ParkingSlot slot : parkingSlots) { //scans and cehck for duplicate plate
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

        for (ParkingSlot slot : parkingSlots) { //search fro first open slot
            if (!slot.isOccupied() && slot.getVehicleType().equalsIgnoreCase(vehicleType)) { //ensure the vehicle type match
                slot.occupySlot(vehicle);
                saveParkingData();
                vehicle.logVehicleDetails();
                System.out.println("Slot number: " + slot.getSlotNumber());
                return;
            }
        }

        System.out.println("No available slots for " + vehicleType); //when no slots available
    }

    public void unparkVehicle(Scanner scanner) {            //removes from park
        System.out.print("Enter slot number to unpark: ");
        int slotNumber;
        while (true) {                                      //loops till valid slot
            if (!scanner.hasNextLine()) {
                return;
            }
            try {
                slotNumber = Integer.parseInt(scanner.nextLine().trim()); //trims sorrounding space to prevent invalidation of valid slot
                break;
            } catch (NumberFormatException e) {     //catch invalid
                System.out.print("Invalid slot number. Please enter a number: ");
            }
        }

        for (ParkingSlot slot : parkingSlots) { //unparks the vechicle on inputed slot number
            if (slot.getSlotNumber() == slotNumber) {
                if (slot.isOccupied()) {
                    slot.freeSlot();    //removes from park
                    saveParkingData();  //update the file
                    System.out.println("Vehicle unparked from slot number: " + slotNumber);
                } else {
                    System.out.println("Slot number " + slotNumber + " is already free."); //when slot is free already
                }
                return;
            }
        }

        System.out.println("Invalid slot number.");
    }
}
