package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Optional;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions.NoItemInArrayException;

/**
 * The Inventory class represents the player's collection of items in the game.
 * It provides functionality to add, remove, and retrieve items from the
 * player's inventory.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Inventory {

    /**
     * A list that holds the items in the inventory.
     */
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Adds a new item to the inventory if it isn't already present. Otherwise,
     * increases that item's quantity by added amount.
     *
     * @param item The item to be added to the inventory.
     */
    public void addItem(Item item) {
        boolean itemExists = false;

        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                i.addQuantity(item.getQuantity());
                if (i.getQuantity() > 0) {
                    itemExists = true;
                } else {
                    try {
                        removeItem(i);
                    } catch (NoItemInArrayException ex) {

                    }
                }
                break;
            }
        }

        if (!itemExists && item.getQuantity() > 0) {
            items.add(item);
        }
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item The item to be removed from the inventory.
     */
    public void removeItem(Item item) throws NoItemInArrayException {

        /*try {
            if (items.contains(item)) {
                items.remove(item);
            } else {
                throw new NoItemInArrayException("The item doesn't exist!");
            }
        } catch (NoItemInArrayException ex) {
            
        }*/
        if (items.contains(item)) {
            items.remove(item);
        } else {
            throw new NoItemInArrayException("The item doesn't exist!");
        }
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
        return items.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Uses a healing item from the inventory to restore player health.
     *
     * @param player The player using the item.
     * @return True if an item was successfully used; false otherwise.
     */
    public boolean useHealingItem(Player player) {
        Optional<Item> healingItem = items.stream()
                .filter(item -> "Healing Potion".equalsIgnoreCase(item.getName()))
                .findFirst();

        if (healingItem.isPresent()) {
            Item item = healingItem.get();
            int healingAmount = 20;
            player.setHealth(player.getHealth() + healingAmount);

            if (player.getHealth() >= 100) {
                player.setHealth(100);
            }

            // Reduce item quantity
            item.setQuantity(item.getQuantity() - 1);
            if (item.getQuantity() <= 0) {
                items.remove(item);
            }

            return true;
        }

        return false; // No healing items found
    }

    /**
     * Adds one or more items to the player's inventory. The method accepts a
     * variable number of Item objects and adds each one to the inventory.
     *
     * @param items The items to be added to the inventory. This can be a single
     * item or multiple items.
     */
    public void addItemsToInventory(Item... items) {
        for (Item item : items) {
            addItem(item);
        }
    }
}
