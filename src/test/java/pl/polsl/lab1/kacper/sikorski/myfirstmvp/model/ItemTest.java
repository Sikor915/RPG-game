package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

/**
 * Unit tests for the Item class.
 * Validates the creation, modification, and error handling of the Item class.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class ItemTest {

    /**
     * Provides invalid arguments for creating items, including null names,
     * empty names, and invalid quantities.
     *
     * @return a stream of invalid item arguments along with their expected exception messages.
     */
    static Stream<org.junit.jupiter.params.provider.Arguments> provideInvalidItems() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        null, 1, Item.Type.POTION, "Name cannot be null or empty."
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "", 1, Item.Type.POTION, "Name cannot be null or empty."
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "Potion", 0, Item.Type.POTION, "Quantity must be greater than zero."
                ),
                org.junit.jupiter.params.provider.Arguments.of(
                        "Potion", -1, Item.Type.POTION, "Quantity must be greater than zero."
                )
        );
    }

    /**
     * Tests adding a positive quantity to an item.
     * Verifies that the quantity and type are updated correctly.
     *
     * @param name     the name of the item
     * @param quantity the initial quantity of the item
     * @param type     the type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Potion, 5, POTION"
    })
    void testAddQuantity(String name, int quantity, Item.Type type) {
        // GIVEN: An item with a specified name, quantity, and type
        Item item = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();

        // WHEN: Adding a positive quantity to the item
        item.addQuantity(3);

        // THEN: Verify the total quantity and type
        assertEquals(8, item.getQuantity(), "After adding, there should be 8 potions.");
        assertEquals(Item.Type.POTION, item.getType(), "The item type should be \"POTION\".");
    }

    /**
     * Tests adding a negative quantity to an item.
     * Verifies that the quantity is reduced correctly.
     *
     * @param name     the name of the item
     * @param quantity the initial quantity of the item
     * @param type     the type of the item
     */
    @ParameterizedTest
    @CsvSource({
        "Potion, 5, POTION"
    })
    void testAddQuantityWithNegativeValue(String name, int quantity, Item.Type type) {
        // GIVEN: An item with a specified name, quantity, and type
        Item item = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();

        // WHEN: Adding a negative quantity to the item
        item.addQuantity(-2);

        // THEN: Verify the total quantity is reduced
        assertEquals(3, item.getQuantity(), "After adding the negative quantity, there should be 3 potions.");
    }

    /**
     * Tests creating invalid items with null or empty names,
     * or invalid quantities.
     * Verifies that the appropriate exception is thrown with the correct message.
     *
     * @param name            the name of the item
     * @param quantity        the quantity of the item
     * @param type            the type of the item
     * @param expectedMessage the expected exception message
     */
    @ParameterizedTest
    @MethodSource("provideInvalidItems")
    void createInvalidItems(String name, int quantity, Item.Type type, String expectedMessage) {
        // GIVEN: Invalid arguments for creating an item

        // WHEN & THEN: Verify that an exception is thrown with the expected message
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Item item = Item.builder()
                    .name(name)
                    .quantity(quantity)
                    .type(type)
                    .build();
        });

        assertEquals(expectedMessage, ex.getMessage(), "Exception message should match the expected error.");
    }
}
