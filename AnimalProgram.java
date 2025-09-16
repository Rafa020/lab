
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract class Animal {
    protected String name;
    protected String dietType;
    protected String sound;
    
    public Animal(String name, String dietType, String sound) {
        this.name = name;
        this.dietType = dietType;
        this.sound = sound;
    }
    
    public abstract String makeSound();
    
    // Encapsulated getters
    public String getDietType() {
        return dietType;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name + " (Diet: " + dietType + ", Sound: " + makeSound() + ")";
    }
}

abstract class Herbivore extends Animal {
    public Herbivore(String name, String sound) {
        super(name, "herbivore", sound);
    }
}

abstract class Carnivore extends Animal {
    public Carnivore(String name, String sound) {
        super(name, "carnivore", sound);
    }
}

abstract class Omnivore extends Animal {
    public Omnivore(String name, String sound) {
        super(name, "omnivore", sound);
    }
}

// Concrete animal classes
class Capybara extends Herbivore {
    public Capybara() {
        super("capybara", "Squeak");
    }
    
    @Override
    public String makeSound() {
        return sound;
    }
}

class Lion extends Carnivore {
    public Lion() {
        super("lion", "Roar!!");
    }
    
    @Override
    public String makeSound() {
        return sound;
    }
}

class Alligator extends Carnivore {
    public Alligator() {
        super("alligator", "Hiss");
    }
    
    @Override
    public String makeSound() {
        return sound;
    }
}

class Hawk extends Carnivore {
    public Hawk() {
        super("hawk", "Screech");
    }
    
    @Override
    public String makeSound() {
        return sound;
    }
}

class AnimalRegistry {
    private Map<String, Animal> animals;
    
    public AnimalRegistry() {
        animals = new HashMap<>();
        initializeAnimals();
    }
    
    private void initializeAnimals() {
        registerAnimal(new Capybara());
        registerAnimal(new Lion());
        registerAnimal(new Alligator());
        registerAnimal(new Hawk());
    }
    
    public void registerAnimal(Animal animal) {
        animals.put(animal.getName().toLowerCase(), animal);
    }
    
    public Animal getAnimal(String name) {
        return animals.get(name.toLowerCase().trim());
    }
    
    public boolean hasAnimal(String name) {
        return animals.containsKey(name.toLowerCase().trim());
    }
    
    public void listAvailableAnimals() {
        System.out.println("Available animals:");
        animals.keySet().forEach(name -> System.out.println("- " + name));
    }
}

// Main program class
public class AnimalProgram {
    private AnimalRegistry registry;
    private Scanner scanner;
    
    public AnimalProgram() {
        registry = new AnimalRegistry();
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("=== Animal Information Program ===");
        System.out.println("Learn about different animals and their diets!");
        
        registry.listAvailableAnimals();
        System.out.println("\nEnter two animals you want to know about:");
        
        System.out.print("First animal: ");
        String animal1 = scanner.nextLine();
        displayAnimalInfo(animal1);
        
        System.out.print("Second animal: ");
        String animal2 = scanner.nextLine();
        displayAnimalInfo(animal2);
        
        System.out.println("\nThank you for using the Animal Information Program!");
    }
    
    private void displayAnimalInfo(String animalName) {
        if (registry.hasAnimal(animalName)) {
            Animal animal = registry.getAnimal(animalName);
            System.out.println(capitalize(animal.getName()) + "s are " + animal.getDietType() + "s");
            System.out.println("They make a " + animal.makeSound() + " sound");
        } else {
            System.out.println("Sorry, I don't know about " + animalName + 
                             ". Please choose from the available animals.");
        }
        System.out.println(); 
    }
    
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public static void main(String[] args) {
        AnimalProgram program = new AnimalProgram();
        program.run();
    }
}