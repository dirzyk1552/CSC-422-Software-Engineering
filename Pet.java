
public class Pet {
	//private fields to hold the name and age 
    private String name;
    private int age;
    //constructor that initializes the pet object
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    //returns pet name and age
    public String toString() {
        return name + " " + age;
    }
}
