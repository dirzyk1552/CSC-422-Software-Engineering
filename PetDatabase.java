import java.util.ArrayList;
import java.util.List;

public class PetDatabase {
	//List to store pet objects
    private List<Pet> pets = new ArrayList<>();
    //method to add a pet
    public void addPet(Pet pet) {
        pets.add(pet);
    }
    //method to show pets in the database
    public void showPets() {
    	//prints the header of the table
        printTableHeader();
        for (int i = 0; i < pets.size(); i++) {
            printPetRow(i, pets.get(i));
        }
        printTableFooter(pets.size());
    }
//method to update a pet
    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) {
            Pet pet = pets.get(id);
            pet.setName(newName);
            pet.setAge(newAge);
        } else {
            System.out.println("Error: ID " + id + " does not exist.");
        }
    }
//method to search a pet by their name
    public void searchPetsByName(String name) {
        List<Pet> matchingPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                matchingPets.add(pet);
            }
        }
        printPets(matchingPets);
    }
//method to search a pet by their age
    public void searchPetsByAge(int age) {
        List<Pet> matchingPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getAge() == age) {
                matchingPets.add(pet);
            }
        }
        printPets(matchingPets);
    }
//method to remove a pet by their id
    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            pets.remove(id);
        } else {
            System.out.println("Error: ID " + id + " does not exist.");
        }
    }
//method to print the list of pets
    private void printPets(List<Pet> petsToPrint) {
        printTableHeader();
        for (Pet pet : petsToPrint) {
            int id = pets.indexOf(pet); // Find the original index for the pet ID
            printPetRow(id, pet);
        }
        printTableFooter(petsToPrint.size());
    }

    // Helper method to print the header of the table
    private void printTableHeader() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
    }

    // Helper method to print a single row of the table
    private void printPetRow(int id, Pet pet) {
        System.out.printf("| %-2d | %-10s | %-3d |\n", id, pet.getName(), pet.getAge());
    }

    // Helper method to print the footer of the table
    private void printTableFooter(int rowCount) {
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", rowCount);
    }
}

