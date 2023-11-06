import java.util.Scanner;

public class Main {
	//scanner to read input from user
    private static final Scanner scanner = new Scanner(System.in);
    //petdatabase to manage pet records
    private static final PetDatabase database = new PetDatabase();

    public static void main(String[] args) {
    	//options for user to choose from
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");
//reading users choice as an integer
            int choice = scanner.nextInt();
            //creating a new line for user to submit
            scanner.nextLine();

            switch (choice) {
                case 1:
                   //showing all pets in the database 
                    database.showPets();
                    break;
                case 2:
                	//adding pets to the database
                	 addPets();
                    break;
                    //updating pets name/age in database
                case 3:
                    updatePets();
                    break;
                    //removing a pet from the database
                case 4:
                	removePet();                  
                    break;
                    //searching up a pet by their name
                case 5:
                    searchPetsByName();
                    break;
                    //searching up a pet by their age
                case 6:
                	searchPetsByAge();                   
                    break;
                    //exiting the program
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                    //response if user inputs an invalid input
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addPets() {
    	//display for when user adds a pet
        System.out.println("add pet (name, age): Enter 'done' to stop:");
        //method to add pet to the database
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Error: Invalid pet entry. Please enter the name and age separated by a space.");
                continue;
            }
            String name = parts[0];
            try {
                int age = Integer.parseInt(parts[1]);
                database.addPet(new Pet(name, age));
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid age. Please enter a valid number for the age.");
            }
        }
    }
//method to update an existing pet in the database
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
//method to search a pet by their name
    private static void searchPetsByName() {
        System.out.print("Enter a name to search: ");
        String name = scanner.nextLine();
        database.searchPetsByName(name);
    }
//method to search a pet by their age
    private static void searchPetsByAge() {
        System.out.print("Enter an age to search: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        database.searchPetsByAge(age);
    }
//method to remove pet
    private static void removePet() {
        database.showPets();
        System.out.print("Enter the pet ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        database.removePet(id);
    }
}
