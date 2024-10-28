package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import java.util.ArrayList;

/**
 * The Inventory class represents the player's collection of items in the game.
 * It provides functionality to add, remove, and retrieve items from the
 * player's inventory.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class Inventory {

    /**
     * A list that holds the items in the inventory
     */
    private ArrayList<Item> items;

    /**
     * Default constructor for the Inventory class. Initializes the inventory as
     * an empty list of items.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Retrieves the list of items in the inventory.
     *
     * @return The list of items in the inventory
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Adds a new item to the inventory if it isn't already present. Otherwise,
     * increases that item's quantity by added amount.
     *
     * @param item The item to be added to the inventory
     */
    public void addItem(Item item) {
        boolean itemExists = false;

        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                i.addQuantity(item.getQuantity());
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            items.add(item);
        }
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item The item to be removed from the inventory
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Retrieves an item from the inventory by its name. This method searches
     * through the list of items and returns the first item that matches the
     * given name.
     *
     * @param name The name of the item to find.
     * @return The item with the specified name, or null if no such item is
     * found.
     */
    public Item getItemByName(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }
}
