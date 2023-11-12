import java.util.Scanner;
import java.io.IOException;

public class Main {
	//scanner for reading users input
    private static final Scanner scanner = new Scanner(System.in);
    private static final PetDatabase database = new PetDatabase();

    public static void main(String[] args) {
    	//loading file 
        try {
            database.loadFromFile("pets.txt");
        } catch (IOException e) {
            System.out.println("Error loading file.");
        }
//Loop for users choices
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add new pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
//handling user's choice
            switch (choice) {
                case 1:
                    database.showPets();
                    break;
                case 2:
                    addPets();
                    break;
                case 3:
                    updatePets();
                    break;
                case 4:
                    removePet();                  
                    break;
                case 5:
                    searchPetsByName();
                    break;
                case 6:
                    searchPetsByAge();                   
                    break;
                case 7:
                    try {
                    	//saving file before exiting
                        database.saveToFile("pets.txt");
                    } catch (IOException e) {
                        System.out.println("Error saving file.");
                    }
                    System.out.println("Goodbye!");
                    //closing the scanner
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
//method to add new pets
    private static void addPets() {
        System.out.println("add pet (name, age): Enter 'done' to stop:");
        while (true) {
        	//checking to see if the database is full
            if (database.getSize() >= 5) {
                System.out.println("Error: Database is full.");
                break;
            }

            System.out.print("> ");
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
//Validating the input format
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Error: Invalid input. Please enter a name and an age.");
                continue;
            }
//Handling the age and input validation
            try {
                int age = Integer.parseInt(parts[1]);
                if (age < 1 || age > 20) {
                    System.out.println("Error: Age must be between 1 and 20.");
                    continue;
                }
                database.addPet(new Pet(parts[0], age));
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid age. Please enter a valid number.");
            }
        }
    }
//method to update pet information
    private static void updatePets() {
        database.showPets();
        System.out.print("Enter the pet ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name and new age: ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            System.out.println("Error: Invalid entry. Please enter the name and age separated by a space.");
            return;
        }
        String newName = parts[0];
        try {
            int newAge = Integer.parseInt(parts[1]);
            database.updatePet(id, newName, newAge);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid age. Please enter a valid number for the age.");
        }
    }
//method to search pets by name
    private static void searchPetsByName() {
        System.out.print("Enter a name to search: ");
        String name = scanner.nextLine();
        database.searchPetsByName(name);
    }
//method to search pets by age
    private static void searchPetsByAge() {
        System.out.print("Enter an age to search: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        database.searchPetsByAge(age);
    }
//method to remove a pet
    private static void removePet() {
        database.showPets();
        System.out.print("Enter the pet ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        database.removePet(id);
    }
}
