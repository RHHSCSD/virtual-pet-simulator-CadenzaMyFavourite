/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;

import java.io.*;
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
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean loggedIn = false;
        int[] maxStats = new int[3]; // maxHealth, maxFood, maxEnergy
        int[] currentStats = new int[3]; // currentHealth, currentFood, currentEnergy
        int money = 0;
        String username = "";
        String petName = "";
        int[] actionCounts = new int[3]; // Tracks counts for play, feed, groom

        // Splash screen
        System.out.println("  /\\_/\\     Jackie CATOPIA");
        System.out.println(" ( o.o )    Virtual Pet Simulator");
        System.out.println("  > ^ <     Welcome!");

        // Login system
        System.out.println("\nPlease log in to proceed:");
        System.out.print("Username: ");
        username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        File userFile = new File(username + ".txt");
        if (userFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
                String storedPassword = br.readLine();
                if (storedPassword.equals(password)) {
                    loggedIn = true;
                    petName = br.readLine();
                    for (int i = 0; i < maxStats.length; i++) {
                        maxStats[i] = Integer.parseInt(br.readLine());
                    }
                    for (int i = 0; i < currentStats.length; i++) {
                        currentStats[i] = Integer.parseInt(br.readLine());
                    }
                    money = Integer.parseInt(br.readLine());
                } else {
                    System.out.println("Incorrect password.");
                    System.exit(0);
                }
            } catch (IOException e) {
                System.out.println("Error reading user file.");
                System.exit(0);
            }
        } else {
            System.out.println("New user. Generating pet...");
            petName = generateNewPet(scanner, random, maxStats);
            loggedIn = true;
            System.arraycopy(maxStats, 0, currentStats, 0, maxStats.length); // Initialize current stats to max stats
        }

        if (!loggedIn) {
            System.out.println("Login failed. Exiting...");
            System.exit(0);
        }

        boolean exitGame = false;

        while (!exitGame) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Play/Interact");
            System.out.println("2. Instructions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String input = scanner.next();
            scanner.nextLine(); // Consume newline

            switch (input.toLowerCase()) {
                case "1":
                case "play":
                case "interact":
                    money += playMiniGames();
                    interactWithPet(scanner, random, currentStats, maxStats, money, actionCounts);
                    break;
                case "2":
                case "instructions":
                    displayInstructions();
                    break;
                case "3":
                case "exit":
                    saveUserData(username, password, petName, maxStats, currentStats, money);
                    displaySummary(actionCounts);
                    exitGame = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to generate new pet and allocate max stats
    public static String generateNewPet(Scanner scanner, Random random, int[] maxStats) {
        System.out.println("Choose a name for your pet:");
        System.out.println("1. Type in the name yourself");
        System.out.println("2. Let the computer generate one for you");
        System.out.print("Enter your choice: ");
        int nameChoice = scanner.nextInt();
        String petName = choosePetName(nameChoice);
        allocatePoints(maxStats);
        System.out.println("Your pet, named " + petName + ", has been born!");
        return petName;
    }

    // Method to choose pet name
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
                    if (i == 0) {
                        petName = petName.toUpperCase();
                    }
                } else {
                    petName += vowels.charAt(random.nextInt(vowels.length()));
                }
            }
        }
        return petName;
    }

    // Method to allocate points to max stats
    public static void allocatePoints(int[] maxStats) {
        Random random = new Random();
        int pointsRemaining = 20;
        for (int i = 0; i < maxStats.length; i++) {
            if (pointsRemaining <= 0) {
                break;
            } else if (i == maxStats.length - 1) {
                maxStats[i] = pointsRemaining;
            } else {
                int points = random.nextInt(pointsRemaining) + 1;
                maxStats[i] = points;
                pointsRemaining -= points;
            }
        }
    }

    // Method to play mini-games and earn money
    public static int playMiniGames() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nChoose a mini-game to play:");
        System.out.println("1. Number Guessing Game");
        System.out.println("2. Matching Game");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                return numberGuessingGame();
            case 2:
                return matchingGame();
            case 3:
                return 0;
            default:
                System.out.println("Invalid choice. No money earned.");
                return 0;
        }
    }

    // Number guessing game
    public static int numberGuessingGame() {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100.");

        while (true) {
            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();
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

    // Matching game
    public static int matchingGame() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String letters = "aabbccdd";
        String shuffled = shuffleString(letters);
        int matches = 0;
        int attempts = 0;
        int maxAttempts = 6; // Arbitrary limit for attempts

        System.out.println("\nWelcome to the Matching Game!");
        System.out.println("Pairs of letters are hidden in the following shuffled string:");
        System.out.println(shuffled);
        System.out.println("Can you find all the matching pairs?");
        System.out.println("You have " + maxAttempts + " attempts.");

        while (attempts < maxAttempts) {
            System.out.print("Enter the positions of the letters to reveal (e.g., 01): ");
            String input = sc.nextLine();
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

    // Shuffle a string
    public static String shuffleString(String str) {
        Random random = new Random();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        return new String(chars);
    }

    // Reveal letters in a string
    public static String revealLetters(String shuffled, int pos1, int pos2) {
        char[] revealed = shuffled.toCharArray();
        revealed[pos1] = shuffled.charAt(pos1);
        revealed[pos2] = shuffled.charAt(pos2);
        return new String(revealed);
    }

    // Interact with the pet
    public static void interactWithPet(Scanner scanner, Random random, int[] currentStats, int[] maxStats, int money, int[] actionCounts) {
        System.out.println("\nPet Interaction Menu:");
        System.out.println("1. Play with your pet");
        System.out.println("2. Feed your pet");
        System.out.println("3. Groom your pet");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                if (money >= 5) {
                    money -= 5;
                    currentStats[2] = Math.min(currentStats[2] + 5, maxStats[2]);
                    actionCounts[0]++;
                    System.out.println("You played with your pet. Energy increased!");
                } else {
                    System.out.println("Not enough money to play with your pet.");
                }
                break;
            case 2:
                if (money >= 5) {
                    money -= 5;
                    currentStats[1] = Math.min(currentStats[1] + 5, maxStats[1]);
                    actionCounts[1]++;
                    System.out.println("You fed your pet. Food increased!");
                } else {
                    System.out.println("Not enough money to feed your pet.");
                }
                break;
            case 3:
                currentStats[0] = Math.min(currentStats[0] + 5, maxStats[0]);
                actionCounts[2]++;
                System.out.println("You groomed your pet. Health increased!");
                break;
            default:
                System.out.println("Invalid choice. No action taken.");
        }
    }

    // Display game instructions
    public static void displayInstructions() {
        System.out.println("Instructions:");
        System.out.println("1. Play with your pet to increase its energy.");
        System.out.println("2. Feed your pet to reduce its hunger.");
        System.out.println("3. Groom your pet to improve its health.");
        System.out.println("Earn money by playing mini-games and use it to take care of your pet.");
    }

    // Save user data to a file
    public static void saveUserData(String username, String password, String petName, int[] maxStats, int[] currentStats, int money) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(username + ".txt"))) {
            pw.println(password);
            pw.println(petName);
            for (int stat : maxStats) {
                pw.println(stat);
            }
            for (int stat : currentStats) {
                pw.println(stat);
            }
            pw.println(money);
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    // Display summary of actions
    public static void displaySummary(int[] actionCounts) {
        System.out.println("\nToday's Summary:");
        System.out.println("Played with your pet " + actionCounts[0] + " times.");
        System.out.println("Fed your pet " + actionCounts[1] + " times.");
        System.out.println("Groomed your pet " + actionCounts[2] + " times.");

        if (actionCounts[0] > 10) {
            System.out.println("Congratulations! You earned the title of Avid Player!");
        }
        if (actionCounts[1] > 10) {
            System.out.println("Congratulations! You earned the title of Avid Feeder!");
        }
        if (actionCounts[2] > 10) {
            System.out.println("Congratulations! You earned the title of Avid Groomer!");
        }
    }
}
