/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
//import java.util.Random;
//import java.util.Scanner;
/**
 *
 * @author michael.roy-diclemen
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean loggedIn = false;
        int[] maxStats = new int[3]; // maxHealth, maxFood, maxEnergy
        int money = 0;
        
        
        // Splash screen
        System.out.println("  /\\_/\\     Jackie CATOPIA");
        System.out.println(" ( o.o )    Virtual Pet Simulator");
        System.out.println("  > ^ <     Welcome!");
        
        // Login system
        for (int attempts = 0; attempts < 3; attempts++) {
            System.out.println("\nPlease log in to proceed:");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            if (username.equals("snoopy") && password.equals("toto")) {
                loggedIn = true;
                break;
            } else {
                System.out.println("Incorrect username or password. Attempts left: " + (2 - attempts));
            }
        }
        
        if (!loggedIn) {
            System.out.println("Maximum login attempts reached. Exiting...");
            System.exit(0);
        }
        
        boolean exitGame = false;

        while (!exitGame) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Play/Interact");
            System.out.println("2. Instructions");
            System.out.println("3. Play Mini-Games to Earn Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            
            switch (input.toLowerCase()) {
                case "1", "play", "interact":
                    System.out.println("Choose a name for your pet:");
                    System.out.println("1. Type in the name yourself");
                    System.out.println("2. Let the computer generate one for you");
                    System.out.print("Enter your choice: ");
                    int nameChoice = scanner.nextInt();
                    String petName = choosePetName(nameChoice);
                    allocatePoints(maxStats, random);
                    System.out.println("Your pet, named " + petName + ", has been born!");
                    // Implement pet interaction logic here
                    break;
                case "2", "instructions":
                    System.out.println("Instructions:");
                    // Display instructions
                    break;
                case "3", "play mini-games", "play games":
                    money += playMiniGames(scanner, random);
                    System.out.println("Your current money: $" + money);
                    break;
                case "4", "exit":
                    System.out.println("Exiting...");
                    exitGame = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static String choosePetName(int nameChoice) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        
        String petName = "";
        
        if (nameChoice == 1) {
            System.out.print("Please enter the name: ");
            petName = scanner.nextLine();
        } else if (nameChoice == 2) {
            String vowels = "aeiou";
            String consonants = "bcdfghjklmnpqrstvwxyz";
            int nameLength = random.nextInt(5) + 4;

            for (int i = 0; i < nameLength; i++) {
                if (i % 2 == 0) {
                    petName += consonants.charAt(random.nextInt(consonants.length()));
                    if(i == 0){
                        petName = petName.toUpperCase();
                    }
                } else {
                    petName += vowels.charAt(random.nextInt(vowels.length()));
                }           
            }
        } 
        return petName;
    }
    public static void allocatePoints(int[] maxStats, Random random) {
        int pointsRemaining = 20;
        for (int i = 0; i < maxStats.length; i++) {
            if (pointsRemaining <= 0) break; // No points left to allocate
            int points = random.nextInt(pointsRemaining) + 1; // Allocate 1 to remaining points
            maxStats[i] = points;
            pointsRemaining -= points;
        }
    }
    public static int playMiniGames(Scanner scanner, Random random) {
        System.out.println("\nChoose a mini-game to play:");
        System.out.println("1. Number Guessing Game");
        System.out.println("2. Matching Game");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                return numberGuessingGame(scanner, random);
            case 2:
                return matchingGame(scanner, random);
            default:
                System.out.println("Invalid choice. No money earned.");
                return 0;
        }
    }

    public static int numberGuessingGame(Scanner scanner, Random random) {
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100.");

        while (true) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            attempts++;

            if (guess < secretNumber) {
                System.out.println("Too low!");
            } else if (guess > secretNumber) {
                System.out.println("Too high!");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                return 10 - attempts; // Example scoring mechanism
            }

            if (attempts >= 10) {
                System.out.println("Sorry, you've used all your attempts.");
                break;
            }
        }
        return 0;
    }
    public static int matchingGame(Scanner scanner, Random random) {
        String letters = "aabbccddeeff";
        String shuffled = shuffleString(letters);
        int matches = 0;
        int attempts = 0;
        int maxAttempts = 10; // Arbitrary limit for attempts

        System.out.println("\nWelcome to the Matching Game!");
        System.out.println("Pairs of letters are hidden in the following shuffled string:");
        System.out.println(shuffled);
        System.out.println("Can you find all the matching pairs?");
        System.out.println("You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Enter the positions of the letters to reveal (e.g., 01): ");
            String input = scanner.nextLine();
            attempts++;

            if (input.length() != 2 || !Character.isDigit(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
                System.out.println("Invalid input. Please enter two digits (e.g., 01).");
                continue;
            }

            int pos1 = Character.getNumericValue(input.charAt(0));
            int pos2 = Character.getNumericValue(input.charAt(1));

            if (pos1 < 0 || pos1 >= shuffled.length() || pos2 < 0 || pos2 >= shuffled.length()) {
                System.out.println("Invalid positions. Please enter valid positions.");
                continue;
            }

            if (shuffled.charAt(pos1) == letters.charAt(pos1) && shuffled.charAt(pos2) == letters.charAt(pos2)) {
                System.out.println("Both positions have already been matched. Please try again.");
            } else if (shuffled.charAt(pos1) == shuffled.charAt(pos2)) {
                System.out.println("Match found!");
                matches++;
                shuffled = revealLetters(shuffled, pos1, pos2);
            } else {
                System.out.println("Not a match. Try again.");
            }
        }

        System.out.println("Game over! You found " + matches + " matches in " + attempts + " attempts.");
        return matches * 5 - attempts; // Example scoring mechanism
    }

    public static String shuffleString(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        return new String(chars);
    }

    public static String revealLetters(String shuffled, int pos1, int pos2) {
        char[] revealed = shuffled.toCharArray();
        revealed[pos1] = shuffled.charAt(pos1);
        revealed[pos2] = shuffled.charAt(pos2);
        return new String(revealed);
    }

    
}