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

        // Splash screen
        System.out.println("  /\\_/\\     Jackie CATOPIA");
        System.out.println(" ( o.o )    Virtual Pet Simulator");
        System.out.println("  > ^ <     Welcome!");

        // Login system
        System.out.println("\nPlease log in to proceed:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        
        
        boolean questionL = true;

        if (!username.equals("snoopy") || !password.equals("toto")) {
            System.out.println("Incorrect username or password. Exiting...");
            System.exit(0);
        }else{
            do{
                System.out.println("\nMain Menu:");
            
                System.out.println("1. Start");
                System.out.println("2. Instructions");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine();
                int choice;
                switch (input.toLowerCase()) {
                    case "1", "start":
                        do{
                            System.out.println("Choose your pet:");                    
                            System.out.println("1. Dog");
                            System.out.println("2. Cat");                  
                            System.out.print("Enter your choice: ");                    
                            String petInput = scanner.nextLine();
                            switch (petInput.toLowerCase()) {
                                case "1", "dog":
                                    choice = 1;
                                    System.out.println("you choose dog.");
                                    questionL = false;
                                    break;
                                case "2", "cat":
                                    choice = 2;
                                    System.out.println("you choose cat.");
                                    questionL = false;
                                    break;
                                default:
                                    System.out.println("wrong input, try again");
                            }
                        }while(questionL); 
                        break;
                    
                    
                    case "2", "instructions":
                        System.out.println("instructions:");
                        break;
                    case "3", "exit":
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }while(questionL); 
        }
        System.out.print("1.give the name by yourself(input 1) 2.let the computer generate one for you(input 2):");
        int nameChoice = scanner.nextInt();
        String petName = "";
        if (nameChoice== 1){
            System.out.print("please input the name:");
            petName = scanner.next();
        }else{
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
        System.out.println("Your pet, named " + petName + ", has been born!");
        
    }
}

        
    

 

