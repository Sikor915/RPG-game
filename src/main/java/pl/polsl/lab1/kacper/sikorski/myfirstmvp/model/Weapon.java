package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

/**
 * Represents a weapon in the game, extending the Item class. This class holds
 * the weapon's damage and type information.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public record Weapon(String name, int quantity, String type, int damage) {

}
