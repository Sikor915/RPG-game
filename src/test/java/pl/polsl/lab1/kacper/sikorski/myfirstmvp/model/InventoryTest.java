package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions.NoItemInArrayException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for the Inventory class. Validates various functionalities such as
 * adding, removing, and retrieving items.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class InventoryTest {

    /**
     * Tests adding multiple items to the inventory.
     * @param name1 Name of first item
     * @param quantity1 Quantity of first item
     * @param type1 Type of first item
     * @param name2 Name of second item
     * @param quantity2 Quantity of second item
     * @param type2 Type of second item
     */
    @ParameterizedTest
    @CsvSource({
        "Potion, 2, POTION, Gold, 10, GOLD"
    })
    void testAddItemsToInventory(String name1, int quantity1, Item.Type type1,
            String name2, int quantity2, Item.Type type2) {
        // GIVEN: An empty inventory
        Inventory inventory = new Inventory();

        // WHEN: Adding two items to the inventory
        inventory.addItemsToInventory(
                Item.builder()
                        .name(name1)
                        .quantity(quantity1)
                        .type(type1)
                        .build(),
                Item.builder()
                        .name(name2)
                        .quantity(quantity2)
                        .type(type2)
                        .build()
        );

        // THEN: Verify that both items were added successfully
        assertEquals(2, inventory.getItems().size(), "There should be 2 items in inventory.");
        assertEquals(name1, inventory.getItems().get(0).getName(), "The name of the first item should be \"Potion\".");
    }

    /**
     * Tests using a healing item from the inventory.
     * @param name Name of the item
     * @param quantity Quantity of the item
     * @param type Type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testUseHealingItem(String name, int quantity, Item.Type type) {
        // GIVEN: A player with a healing item in their inventory
        Player player = new Player();
        Inventory inventory = player.getPlayerInventory();
        inventory.addItem(Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build());

        // WHEN: Using a healing item
        boolean used = inventory.useHealingItem(player);

        // THEN: Verify the item is consumed and its quantity is reduced
        assertTrue(used, "The item should be consumed.");
        assertEquals(1, inventory.getItems().get(0).getQuantity(), "There should be one Potion left.");
    }

    /**
     * Tests removing an item from the inventory.
     * @param name Name of the item
     * @param quantity Quantity of the item
     * @param type Type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testRemoveItem(String name, int quantity, Item.Type type) {
        // GIVEN: An inventory with one item
        Inventory inventory = new Inventory();
        Item potion = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();
        inventory.addItem(potion);

        // WHEN: Removing the item
        try {
            inventory.removeItem(potion);
        } catch (NoItemInArrayException ex) {
            // Handle exception, not expected here
        }

        // THEN: Verify the inventory is empty
        assertTrue(inventory.getItems().isEmpty(), "The inventory should be empty.");
    }

    /**
     * Tests retrieving an item by its name.
     * @param name Name of the item
     * @param quantity Quantity of the item
     * @param type Type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testGetItemByName(String name, int quantity, Item.Type type) {
        // GIVEN: An inventory with one specific item
        Inventory inventory = new Inventory();
        Item potion = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();
        inventory.addItem(potion);

        // WHEN: Retrieving the item by name
        Item retrievedItem = inventory.getItemByName("Healing Potion");

        // THEN: Verify the retrieved item is correct
        assertNotNull(retrievedItem, "The item should be properly retrieved.");
        assertEquals("Healing Potion", retrievedItem.getName(), "The retrieved item name should be \"Healing Potion\".");
        assertEquals(2, retrievedItem.getQuantity(), "The quantity of the retrieved item should be equal to 2.");
    }

    /**
     * Tests removing an item that does not exist in the inventory.
     * @param name Name of the item
     * @param quantity Quantity of the item
     * @param type Type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testRemoveNULLItem(String name, int quantity, Item.Type type) {
        // GIVEN: An inventory without the specific item
        Inventory inventory = new Inventory();
        Item potion = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();

        // WHEN & THEN: Attempt to remove a non-existing item, expecting an exception
        assertThrows(NoItemInArrayException.class, () -> {
            inventory.removeItem(potion); // No such item in inventory
        }, "Trying to remove an item that doesn't exist in inventory should throw an exception.");
    }

    /**
     * Tests adding an item with a non-positive quantity.
     * @param name Name of the item
     * @param quantity Quantity of the item
     * @param type Type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Gold, 0, GOLD",
        "Gold, -10, GOLD"
    })
    void testAddItemWithNonPositiveQuantity(String name, int quantity, Item.Type type) {
        // GIVEN: An empty inventory
        Inventory inventory = new Inventory();

        // WHEN: Attempting to add an item with a non-positive quantity
        try {
            Item item = Item.builder()
                    .name(name)
                    .quantity(quantity)
                    .type(type)
                    .build();

            inventory.addItem(item);
        } catch (IllegalArgumentException ex) {
            // Handle exception as expected
        }

        // THEN: Verify the item was not added
        assertEquals(0, inventory.getItems().size(), "The item should not be added.");
    }

    /**
     * Tests adding an item with zero quantity and verifies proper behavior.
     * @param name1 Name of first item
     * @param quantity1 Quantity of first item
     * @param type1 Type of first item
     * @param name2 Name of second item
     * @param quantity2 Quantity of second item
     * @param type2 Type of second item
     */
    @ParameterizedTest
    @CsvSource({
        "Gold, 1, GOLD, Gold, 0, GOLD"
    })
    void testAddZeroQuantity(String name1, int quantity1, Item.Type type1,
            String name2, int quantity2, Item.Type type2) {
        // GIVEN: An inventory with one valid item
        Inventory inventory = new Inventory();
        Item item = Item.builder()
                .name(name1)
                .quantity(quantity1)
                .type(type1)
                .build();
        inventory.addItem(item);

        // WHEN: Attempting to add another item with zero quantity
        try {
            Item item2 = Item.builder()
                    .name(name2)
                    .quantity(quantity2)
                    .type(type2)
                    .build();

            inventory.addItem(item2);
        } catch (IllegalArgumentException ex) {
            // Handle exception as expected
        }

        // THEN: Verify only the valid item is in the inventory
        assertEquals(1, inventory.getItems().size(), "The item should be added properly.");
        assertEquals(1, inventory.getItems().get(0).getQuantity(), "The quantity of the item should be 1.");
    }
}
