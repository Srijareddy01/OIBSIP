import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int score = 0;
        int rounds = 3; // You can modify the number of rounds
        
        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("You have " + rounds + " rounds to play. Let's begin!");
        
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round + ":");
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attempts = 5; // Maximum number of attempts
            boolean guessed = false;
            
            for (int attempt = 1; attempt <= attempts; attempt++) {
                System.out.print("Attempt " + attempt + " - Guess the number (1-100): ");
                int playerGuess = scanner.nextInt();
                
                if (playerGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number!");
                    score += (attempts - attempt + 1) * 10; // More points for fewer attempts
                    guessed = true;
                    break;
                } else if (playerGuess < numberToGuess) {
                    System.out.println("The number is higher than " + playerGuess);
                } else {
                    System.out.println("The number is lower than " + playerGuess);
                }
            }
            
            if (!guessed) {
                System.out.println("Sorry, you've used all attempts. The number was: " + numberToGuess);
            }
        }
        
        System.out.println("\nGame over! Your total score is: " + score);
        scanner.close();
    }
}
