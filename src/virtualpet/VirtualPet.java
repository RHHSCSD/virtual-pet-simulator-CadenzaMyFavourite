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
        int maxHealth = 8;
        int maxFood = 5;
        int maxEnergy = 7;
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
                    System.out.println("Your pet, named " + petName + ", has been born!");
                    // Implement pet interaction logic here
                    break;
                case "2", "instructions":
                    System.out.println("Instructions:");
                    // Display instructions
                    break;
                case "3", "play mini-games", "play games":
                   // money += playMiniGames(scanner, random);
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
    
}