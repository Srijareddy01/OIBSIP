import java.util.ArrayList;
import java.util.Scanner;

// Main ATM Class
public class Main {

    public static void main(String[] args) {
        // Create a scanner for input
        Scanner sc = new Scanner(System.in);

        // Create a new BankAccount
        BankAccount account = new BankAccount("Srija", "123456", 1234);

        System.out.println("Welcome to the ATM System!");

        // Login Process
        System.out.print("Enter User ID: ");
        String userID = sc.nextLine();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (account.validateLogin(userID, pin)) {
            System.out.println("Login Successful!");
            boolean exit = false;

            while (!exit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        account.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        sc.nextLine(); // Consume the newline
                        String recipientID = sc.nextLine();
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = sc.nextDouble();
                        account.transfer(recipientID, transferAmount);
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN.");
        }

        sc.close();
    }
}

// Class for handling the user's bank account
class BankAccount {
    private String accountHolder;
    private String userID;
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public BankAccount(String accountHolder, String userID, int pin) {
        this.accountHolder = accountHolder;
        this.userID = userID;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Validate user login
    public boolean validateLogin(String userID, int pin) {
        return this.userID.equals(userID) && this.pin == pin;
    }

    // Method to print transaction history
    public void printTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions made yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal of Rs." + amount + " successful.");
            transactionHistory.add("Withdrawn: Rs." + amount);
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit of Rs." + amount + " successful.");
        transactionHistory.add("Deposited: Rs." + amount);
    }

    // Method to transfer money
    public void transfer(String recipientID, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance to transfer.");
        } else {
            balance -= amount;
            System.out.println("Transferred Rs." + amount + " to User ID: " + recipientID);
            transactionHistory.add("Transferred: Rs." + amount + " to " + recipientID);
        }
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }
}
