import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PetDatabase {
	//list to store pet objects
    private List<Pet> pets = new ArrayList<>();
//method to add a pet
    public void addPet(Pet pet) {
        if (pets.size() < 5) {
            pets.add(pet);
        } else {
            System.out.println("Error: Database is full.");
        }
    }
//method to display pets
    public void showPets() {
        printTableHeader();
        for (int i = 0; i < pets.size(); i++) {
            printPetRow(i, pets.get(i));
        }
        printTableFooter(pets.size());
    }
//method to update pet name and age
    public void updatePet(int id, String newName, int newAge) {
        if (id >= 0 && id < pets.size()) {
            Pet pet = pets.get(id);
            pet.setName(newName);
            pet.setAge(newAge);
        } else {
            System.out.println("Error: ID " + id + " does not exist.");
        }
    }
//method to search pet by name
    public void searchPetsByName(String name) {
        List<Pet> matchingPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                matchingPets.add(pet);
            }
        }
        printPets(matchingPets);
    }
//method to search pet by age
    public void searchPetsByAge(int age) {
        List<Pet> matchingPets = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getAge() == age) {
                matchingPets.add(pet);
            }
        }
        printPets(matchingPets);
    }
//method to remove pet
    public void removePet(int id) {
        if (id >= 0 && id < pets.size()) {
            pets.remove(id);
        } else {
            System.out.println("Error: ID " + id + " does not exist.");
        }
    }
//method to get amount of pets in database
    public int getSize() {
        return pets.size();
    }
//writing pet information to the file
    public void saveToFile(String filename) throws IOException {
        File file = new File(filename);
        //creating new file if one doesn't exist
        file.createNewFile(); 
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Pet pet : pets) {
                writer.write(pet.getName() + "," + pet.getAge());
                writer.newLine();
            }
        }
    }
//method to load pet info from file
    public void loadFromFile(String filename) throws IOException {
    	//clearing existing data
        pets.clear();
        //reading each line from the file and adding it to the list
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    addPet(new Pet(parts[0], Integer.parseInt(parts[1])));
                }
            }
        }
    }
//helper method
    private void printPets(List<Pet> petsToPrint) {
        printTableHeader();
        for (Pet pet : petsToPrint) {
            int id = pets.indexOf(pet); 
            printPetRow(id, pet);
        }
        printTableFooter(petsToPrint.size());
    }
//header of the table
    private void printTableHeader() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
    }
//method to print out a row from the database
    private void printPetRow(int id, Pet pet) {
        System.out.printf("| %-2d | %-10s | %-3d |\n", id, pet.getName(), pet.getAge());
    }
//footer of the table
    private void printTableFooter(int rowCount) {
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", rowCount);
    }
}
