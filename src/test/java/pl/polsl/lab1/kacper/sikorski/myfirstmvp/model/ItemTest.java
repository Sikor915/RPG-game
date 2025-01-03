package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

/**
 * Unit tests for the Item class.
 *
 * @author sikor
 * @version 1.0
 */
public class ItemTest {

    static Stream<org.junit.jupiter.params.provider.Arguments> provideInvalidItems() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(null, 1, Item.Type.POTION, "Name cannot be null or empty."),
                org.junit.jupiter.params.provider.Arguments.of("", 1, Item.Type.POTION, "Name cannot be null or empty."),
                org.junit.jupiter.params.provider.Arguments.of("Potion", 0, Item.Type.POTION, "Quantity must be greater than zero."),
                org.junit.jupiter.params.provider.Arguments.of("Potion", -1, Item.Type.POTION, "Quantity must be greater than zero.")
        );
    }

    @ParameterizedTest
    @CsvSource({
        "Potion, 5, POTION",})
    void testAddQuantity(String name, int quantity, Item.Type type) {
        // GIVEN
        Item item = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();

        // WHEN
        item.addQuantity(3);

        // THEN
        assertEquals(8, item.getQuantity(), "After adding, there should be 8 potions.");
        assertEquals(Item.Type.POTION, item.getType(), "The item type should be \"POTION\".");
    }

    @ParameterizedTest
    @CsvSource({
        "Potion, 5, POTION",})
    void testAddQuantityWithNegativeValue(String name, int quantity, Item.Type type) {
        // GIVEN
        Item item = Item.builder()
                .name(name)
                .quantity(quantity)
                .type(type)
                .build();

        // WHEN
        item.addQuantity(-2);

        // THEN
        assertEquals(3, item.getQuantity(), "After adding the negative quantity, there should be 3 potions.");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidItems")
    void createInvalidItems(String name, int quantity, Item.Type type, String expectedMessage) {
        // GIVEN

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            Item item = Item.builder()
                    .name(name)
                    .quantity(quantity)
                    .type(type)
                    .build();
        }
        );

        // WHEN & THEN
        assertEquals(expectedMessage, ex.getMessage(), "Exception message should match the expected error");
    }
}
