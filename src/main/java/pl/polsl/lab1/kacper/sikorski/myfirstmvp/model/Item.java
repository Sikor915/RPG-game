package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an item with a name and quantity. This class is simplified using
 * Lombok annotations for generating boilerplate code.
 *
 * @author Kacper Sikorski
 * @version 1.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    /**
     * The name of the item.
     */
    private String name;

    /**
     * The quantity of the item.
     */
    private int quantity;

    /**
     * The type of an item.
     */
    private Type type;

    /**
     * ENUM to represent item types.
     */
    public enum Type {
        WEAPON,
        POTION,
        ARMOR,
        DEVITEM,
        GOLD
    }

    /**
     * Adds a specified quantity to the current quantity of the item.
     *
     * @param quantity The amount to be added to the existing quantity.
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
