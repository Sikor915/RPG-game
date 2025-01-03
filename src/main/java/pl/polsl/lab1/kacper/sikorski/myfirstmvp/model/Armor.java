package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.*;

/**
 * Represents an armor item in the game. Extends the {@link Item} class to
 * include specific functionality and properties for armor, such as damage
 * reduction.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
public class Armor extends Item {

    /**
     * The amount of damage this armor can reduce during combat.
     */
    private int damageReduction;

    /**
     * Constructs an instance of the Armor class using the builder pattern.
     *
     * @param name The name of the armor item.
     * @param quantity The number of this armor item in the inventory.
     * @param type The type of the item (must be {@link Item.Type#ARMOR}).
     * @param damageReduction The damage reduction value provided by the armor.
     */
    @Builder(builderMethodName = "armorBuilder")
    public Armor(String name, int quantity, Type type, int damageReduction) {
        super(name, quantity, type); // Call the parent constructor
        this.damageReduction = damageReduction;
    }
}
