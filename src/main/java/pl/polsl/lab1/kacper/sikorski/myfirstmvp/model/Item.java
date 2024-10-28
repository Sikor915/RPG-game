package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

/**
 * Represents an item with a name and quantity. This class provides methods to
 * get and set the name and quantity of the item.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
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
     * Constructs an Item with the specified name and quantity.
     *
     * @param name the name of the item
     * @param quantity the quantity of the item
     */
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of the item.
     *
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity the new quantity of the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
