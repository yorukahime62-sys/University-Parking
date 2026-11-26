import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        parkingManager parkingSystem = new parkingManager();
        parkingSystem.loadOrInitializeSlots();
        int choice = 0;

        do {
            System.out.println("\n=====University Parking System=====");
            System.out.println("1. Display Available Parking Slots");
            System.out.println("2. Park a Vehicle");
            System.out.println("3. Unpark a Vehicle");
            System.out.println("4. Display Occupied Parking Slots");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println();
                    parkingSystem.displayAvailableSlots();
                    break;
                case 2:
                    System.out.println();
                    parkingSystem.parkVehicle(scanner);
                    break;
                case 3:
                    System.out.println();
                    parkingSystem.unparkVehicle(scanner);
                    break;
                case 4:
                    System.out.println();
                    parkingSystem.diplayOccupiedSlots();
                    break;
                case 5:
                    parkingSystem.saveParkingData();
                    System.out.println();
                    System.out.println("Saved data. Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);


        scanner.close();
    }
}
