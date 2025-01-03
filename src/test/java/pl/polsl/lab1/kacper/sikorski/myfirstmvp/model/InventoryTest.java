package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions.NoItemInArrayException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

/**
 * Tests for the Inventory class.
 *
 * @author sikor
 * @version 1.0
 */
public class InventoryTest {

    static Stream<org.junit.jupiter.params.provider.Arguments> provideInvalidItems() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(null, 1, Item.Type.POTION),
                org.junit.jupiter.params.provider.Arguments.of("", 1, Item.Type.POTION),
                org.junit.jupiter.params.provider.Arguments.of("Potion", 0, Item.Type.POTION),
                org.junit.jupiter.params.provider.Arguments.of("Potion", -1, Item.Type.POTION)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "Potion, 2, POTION, Gold, 10, GOLD"
    })
    void testAddItemsToInventory(String name1, int quantity1, Item.Type type1,
            String name2, int quantity2, Item.Type type2) {
        // GIVEN
        Inventory inventory = new Inventory();

        // WHEN
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

        // THEN
        assertEquals(2, inventory.getItems().size(), "There should be 2 items in inventory.");
        assertEquals(name1, inventory.getItems().get(0).getName(), "The name of the first item should be \"Potion\".");
    }

    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testUseHealingItem(String name, int quantity, Item.Type type) {
        // GIVEN
        Player player = new Player();
        Inventory inventory = player.getPlayerInventory();
        inventory.addItem(Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build());

        // WHEN
        boolean used = inventory.useHealingItem(player);

        // THEN
        assertTrue(used, "The item should be consumed.");
        assertEquals(1, inventory.getItems().get(0).getQuantity(), "There should be one Potion left.");
    }

    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testRemoveItem(String name, int quantity, Item.Type type) {
        // GIVEN
        Inventory inventory = new Inventory();
        Item potion = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();
        inventory.addItem(potion);

        // WHEN
        try {
            inventory.removeItem(potion);
        } catch (NoItemInArrayException ex) {

        }
        // THEN
        assertTrue(inventory.getItems().isEmpty(), "The inventory should be empty.");
    }

    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testGetItemByName(String name, int quantity, Item.Type type) {
        // GIVEN
        Inventory inventory = new Inventory();
        Item potion = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();
        inventory.addItem(potion);

        // WHEN
        Item retrievedItem = inventory.getItemByName("Healing Potion");

        // THEN
        assertNotNull(retrievedItem, "The item should be properly retrieved.");
        assertEquals("Healing Potion", retrievedItem.getName(), "The retrieved item name should be \"Healing Potion\".");
        assertEquals(2, retrievedItem.getQuantity(), "The quantity of the retrieved item should be equal to 2.");
    }

    @ParameterizedTest
    @CsvSource({
        "Healing Potion, 2, POTION"
    })
    void testRemoveNULLItem(String name, int quantity, Item.Type type) {
        // GIVEN
        Inventory inventory = new Inventory();
        Item potion = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();
        // WHEN & THEN
        assertThrows(NoItemInArrayException.class, () -> {
            inventory.removeItem(potion); // No such item in inventory
        }, "Trying to remove an item that doesn't exist in inventory should throw an exception.");
    }

    @ParameterizedTest
    @CsvSource({
        "Gold, 0, GOLD",
        "Gold, -10, GOLD"
    })
    void testAddItemWithNonPositiveQuantity(String name, int quantity, Item.Type type) {
        // GIVEN
        Inventory inventory = new Inventory();
        // WHEN
        try {
            Item item = Item.builder()
                    .name(name)
                    .quantity(quantity)
                    .type(type)
                    .build();

            inventory.addItem(item);
        } catch (IllegalArgumentException ex) {

        }

        // THEN
        assertEquals(0, inventory.getItems().size(), "The item should not be added.");
    }

    @ParameterizedTest
    @CsvSource({
        "Gold, 1, GOLD, Gold, 0, GOLD"
    })
    void testAddZeroQuantity(String name1, int quantity1, Item.Type type1,
            String name2, int quantity2, Item.Type type2) {
        // GIVEN
        Inventory inventory = new Inventory();
        Item item = Item.builder()
                .name(name1)
                .quantity(quantity1)
                .type(type1)
                .build();
        inventory.addItem(item);
        // WHEN
        try {
            Item item2 = Item.builder()
                    .name(name2)
                    .quantity(quantity2)
                    .type(type2)
                    .build();

            inventory.addItem(item2);
        } catch (IllegalArgumentException ex) {

        }

        // THEN
        assertEquals(1, inventory.getItems().size(), "The item should be added properly.");
        assertEquals(1, inventory.getItems().get(0).getQuantity(), "The quantity of the item should be 1");
    }
}
