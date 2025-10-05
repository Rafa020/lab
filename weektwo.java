import java.util.*;

// Strategy interface
interface AttackBehavior {
    void attack();
}

// Concrete strategies
class SwordAttack implements AttackBehavior {
    public void attack() {
        System.out.println("Swings a sword!");
    }
}

class BowAttack implements AttackBehavior {
    public void attack() {
        System.out.println("Shoots an arrow!");
    }
}

class FireballAttack implements AttackBehavior {
    public void attack() {
        System.out.println("Casts a fireball!");
    }
}

class DaggerAttack implements AttackBehavior {
    public void attack() {
        System.out.println("Strikes swiftly with daggers!");
    }
}

class AxeAttack implements AttackBehavior {
    public void attack() {
        System.out.println("Smashes with a mighty axe!");
    }
}

// Abstract Character class
abstract class Character {
    protected String name;
    protected AttackBehavior attackBehavior;

    public Character(String name, AttackBehavior attackBehavior) {
        this.name = name;
        this.attackBehavior = attackBehavior;
    }

    public void performAttack() {
        System.out.print("The " + name + " ");
        attackBehavior.attack();
    }

    public void setAttackBehavior(AttackBehavior newBehavior) {
        this.attackBehavior = newBehavior;
    }

    public String getName() {
        return name;
    }
}

// Concrete Character classes
class Knight extends Character {
    public Knight() {
        super("Knight", new SwordAttack());
    }
}

class Archer extends Character {
    public Archer() {
        super("Archer", new BowAttack());
    }
}

class Mage extends Character {
    public Mage() {
        super("Mage", new FireballAttack());
    }
}

class Rogue extends Character {
    public Rogue() {
        super("Rogue", new DaggerAttack());
    }
}

class Barbarian extends Character {
    public Barbarian() {
        super("Barbarian", new AxeAttack());
    }
}

// Main game class
public class StrategyGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<String, Character> characters = new HashMap<>();
        characters.put("knight", new Knight());
        characters.put("archer", new Archer());
        characters.put("mage", new Mage());
        characters.put("rogue", new Rogue());
        characters.put("barbarian", new Barbarian());

        System.out.println("Hello!");
        System.out.println("Choose your class: " + characters.keySet());
        String choice = input.nextLine().toLowerCase();

        Character player = characters.get(choice);

        if (player == null) {
            System.out.println("You did not choose a valid character!");
            input.close();
            return;
        }

        System.out.println("\nOh no! A Dragon!!!");
        System.out.println("Attack it!!");
        player.performAttack();

        // ✅ TODO Implementation: allow weapon change
        System.out.println("\nWould you like to change weapons? (yes/no)");
        if (input.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Choose new weapon: sword, bow, fireball, dagger, axe");
            String weaponChoice = input.nextLine().toLowerCase();
            switch (weaponChoice) {
                case "sword": player.setAttackBehavior(new SwordAttack()); break;
                case "bow": player.setAttackBehavior(new BowAttack()); break;
                case "fireball": player.setAttackBehavior(new FireballAttack()); break;
                case "dagger": player.setAttackBehavior(new DaggerAttack()); break;
                case "axe": player.setAttackBehavior(new AxeAttack()); break;
                default: System.out.println("Invalid weapon choice!"); input.close(); return;
            }
            System.out.println("\nYou changed your weapon!");
            player.performAttack();
        }

        input.close();
    }
}
