package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for the Armor class
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class ArmorTest {

    /**
     * Parameterized test for verifying the behavior of the Armor class's damage
     * reduction. This test ensures that the `damageReduction` field is
     * correctly initialized and retrieved for various input values, including
     * edge cases (e.g., negative or zero values).
     *
     * @param name The name of the armor being tested.
     * @param damageReduction The damage reduction value provided during the
     * armor's creation.
     * @param expectedReduction The expected damage reduction value after
     * initialization.
     */
    @ParameterizedTest
    @CsvSource({
        "Knight's Armor, 5, 5", // Regular armor with a positive reduction value.
        "Mage's Robe, 3, 3", // Another valid positive value for reduction.
        "Leather Vest, 0, 0", // Edge case: zero reduction value.
        "Cursed Armor, -5, -5", // Edge case: negative reduction value.
        "Ultra Cursed Armor 5000, -9999, -9999" // Extreme negative value for reduction.
    })
    void testArmorDamageReduction(String name, int damageReduction, int expectedReduction) {
        // GIVEN: An Armor object is created using the provided name and damageReduction value.
        Armor armor = Armor.armorBuilder()
                .name(name)
                .quantity(1)
                .type(Item.Type.ARMOR)
                .damageReduction(damageReduction)
                .build();

        // WHEN: The actual damage reduction value is retrieved from the Armor object.
        int actualDamageReduction = armor.getDamageReduction();

        // THEN: The actual damage reduction should match the expected value.
        assertEquals(expectedReduction, actualDamageReduction, "Armor reduction should match expected value.");
    }
}
