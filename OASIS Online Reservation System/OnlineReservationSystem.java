import java.util.Scanner;
import java.util.HashMap;

public class OnlineReservationSystem {

    // Data storage (in-memory)
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<String, String> reservations = new HashMap<>();
    private static HashMap<String, String> cancellations = new HashMap<>();
    private static int pnrCounter = 1000;

    // Main Method
    public static void main(String[] args) {
        // Create a scanner object to take inputs
        Scanner sc = new Scanner(System.in);
        // Add a default user for login we can change further 
        users.put("admin", "password");

        System.out.println("Welcome to the Online Reservation System!");
        
        // User Login
        System.out.print("Enter Login ID: ");
        String loginID = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (authenticateUser(loginID, password)) {
            System.out.println("Login successful!");

            boolean exit = false;

            while (!exit) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        makeReservation(sc);
                        break;
                    case 2:
                        cancelReservation(sc);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Thank you for using the Online Reservation System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid login credentials. Exiting system.");
        }
        sc.close();
    }

    // Method to authenticate user
    public static boolean authenticateUser(String loginID, String password) {
        return users.containsKey(loginID) && users.get(loginID).equals(password);
    }

    // Method to make a reservation
    public static void makeReservation(Scanner sc) {
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        String trainNumber = sc.nextLine();
        System.out.print("Enter Class Type (e.g., Sleeper, AC): ");
        String classType = sc.nextLine();
        System.out.print("Enter Date of Journey (dd-mm-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter Source (From): ");
        String from = sc.nextLine();
        System.out.print("Enter Destination (To): ");
        String to = sc.nextLine();

        String pnr = "PNR" + (++pnrCounter); // Generate a new PNR
        reservations.put(pnr, name + ", " + trainNumber + ", " + classType + ", " + date + ", " + from + ", " + to);
        
        System.out.println("Reservation successful! Your PNR is: " + pnr);
    }

    // Method to cancel a reservation
    public static void cancelReservation(Scanner sc) {
        System.out.print("Enter PNR Number to Cancel: ");
        String pnr = sc.nextLine();

        if (reservations.containsKey(pnr)) {
            System.out.println("Reservation Details: " + reservations.get(pnr));
            System.out.print("Do you want to confirm cancellation? (yes/no): ");
            String confirmation = sc.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                cancellations.put(pnr, reservations.get(pnr)); // Save cancelled details
                reservations.remove(pnr); // Remove reservation
                System.out.println("Reservation successfully canceled.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR Number. No reservation found.");
        }
    }
}
