import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExaminationSystem {

    // Data for login and questions
    private static HashMap<String, String> userData = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String loggedInUser = null;
    private static int score = 0;
    private static int questionIndex = 0;
    private static boolean autoSubmitted = false;
    private static Timer timer;

    // Sample questions
    private static String[][] questions = {
        {"Who invented Java?", "James Gosling", "Dennis Ritchie", "Guido van Rossum", "Bjarne Stroustrup", "1"},
        {"Which company owns Java now?", "Microsoft", "Google", "Oracle", "Apple", "3"},
        {"What does JVM stand for?", "Java Virtual Machine", "Java Variable Method", "Java Visual Mode", "Java Version Manager", "1"},
        {"What is the file extension of compiled Java files?", ".class", ".java", ".cpp", ".py", "1"}
    };

    public static void main(String[] args) {
        // Pre-load a user for login demonstration
        userData.put("user1", "password1");

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Update Profile/Password");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userData.containsKey(username) && userData.get(username).equals(password)) {
            System.out.println("Login successful!");
            loggedInUser = username;
            startExam();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void updateProfile() {
        System.out.print("Enter current username: ");
        String username = scanner.nextLine();
        System.out.print("Enter current password: ");
        String password = scanner.nextLine();

        if (userData.containsKey(username) && userData.get(username).equals(password)) {
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            userData.remove(username);
            userData.put(newUsername, newPassword);
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void startExam() {
        score = 0;
        questionIndex = 0;
        autoSubmitted = false;
        startTimer();

        while (questionIndex < questions.length && !autoSubmitted) {
            askQuestion();
        }

        if (!autoSubmitted) {
            submitExam();
        }
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            int timeLeft = 60; // 1 minute

            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                } else {
                    System.out.println("\nTime is up! Auto-submitting your answers.");
                    autoSubmitted = true;
                    timer.cancel();
                    submitExam();
                }
            }
        }, 0, 1000);
    }

    private static void askQuestion() {
        String[] question = questions[questionIndex];
        System.out.println("Q" + (questionIndex + 1) + ": " + question[0]);
        for (int i = 1; i <= 4; i++) {
            System.out.println(i + ". " + question[i]);
        }
        System.out.print("Enter your answer (1-4): ");
        int answer = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (String.valueOf(answer).equals(question[5])) {
            score++;
        }
        questionIndex++;
    }

    private static void submitExam() {
        System.out.println("Exam submitted.");
        System.out.println("Your score: " + score + "/" + questions.length);
        timer.cancel();
        logout();
    }

    private static void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }
}

