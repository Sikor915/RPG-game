package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

/**
 * Represents a weapon in the game, extending the Item class. This class holds
 * the weapon's damage and type information.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class Weapon extends Item {

    /**
     * The damage value of the weapon.
     */
    private int damage;

    /**
     * The type of the weapon (e.g., Melee, Ranged).
     */
    private String type;

    /**
     * Constructs a Weapon with the specified name, quantity, type, and damage.
     *
     * @param name the name of the weapon
     * @param quantity the quantity of the weapon
     * @param type the type of the weapon
     * @param damage the damage value of the weapon
     */
    public Weapon(String name, int quantity, String type, int damage) {
        super(name, quantity);
        this.type = type;
        this.damage = damage;
    }

    /**
     * Returns the type of the weapon.
     *
     * @return the type of the weapon
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the damage value of the weapon.
     *
     * @return the damage value of the weapon
     */
    public int getDamage() {
        return damage;
    }
}
