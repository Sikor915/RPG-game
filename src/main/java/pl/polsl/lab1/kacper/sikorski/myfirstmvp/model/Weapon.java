package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.*;
import java.lang.IllegalArgumentException;

/**
 * Represents a weapon in the game, extending the Item class. This class holds
 * the weapon's damage and type information.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weapon extends Item {

    /**
     * The damage of the weapon.
     */
    private int damage;

    /**
     * Represents a weapon item in the game, extending the base Item class. This
     * class adds a damage attribute to the item, allowing it to affect
     * gameplay.
     *
     * @param name The name of the weapon.
     * @param quantity The quantity of the weapon in the player's inventory.
     * @param damage The amount of damage the weapon can deal.
     * @param type The type of the item (which could be WEAPON, ARMOR, etc.)
     */
    @Builder(builderMethodName = "weaponBuilder")
    public Weapon(String name, int quantity, int damage, Type type) {
        super(name, quantity, type);
        if (damage > 0) {
            this.damage = damage;
        } else {
            throw new IllegalArgumentException("The damage can't be negative!");
        }
    }
}
