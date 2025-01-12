package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the Weapon class.
 * Validates the creation and behavior of Weapon objects,
 * including inheritance from the Item class and error handling.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class WeaponTest {

    /**
     * Tests the creation of a Weapon object using the builder pattern.
     * Verifies that all attributes are correctly set during object construction.
     *
     * @param name     the name of the weapon
     * @param quantity the quantity of the weapon
     * @param damage   the damage dealt by the weapon
     * @param type     the type of the item (e.g., "WEAPON")
     */
    @ParameterizedTest
    @CsvSource({
        "Sword, 1, 15, WEAPON"
    })
    void testWeaponBuilder(String name, int quantity, int damage, Item.Type type) {
        // GIVEN-WHEN: Create a Weapon object using the builder
        Weapon weapon = Weapon.weaponBuilder()
                .name(name)
                .quantity(quantity)
                .damage(damage)
                .type(type)
                .build();

        // THEN: Validate that all attributes are correctly initialized
        assertNotNull(weapon, "The weapon should be properly created.");
        assertEquals(name, weapon.getName(), "The weapon's name should match the given value.");
        assertEquals(quantity, weapon.getQuantity(), "The weapon's quantity should match the given value.");
        assertEquals(damage, weapon.getDamage(), "The weapon's damage should match the given value.");
        assertEquals(type, weapon.getType(), "The weapon's type should match the given value.");
    }

    /**
     * Tests that the Weapon class correctly inherits from the Item class.
     * Verifies that a Weapon object retains all attributes from its parent class.
     *
     * @param name     the name of the weapon
     * @param quantity the quantity of the weapon
     * @param damage   the damage dealt by the weapon
     * @param type     the type of the item (e.g., "WEAPON")
     */
    @ParameterizedTest
    @CsvSource({
        "Axe, 2, 20, WEAPON"
    })
    void testWeaponInheritanceFromItem(String name, int quantity, int damage, Item.Type type) {
        // GIVEN-WHEN: Create a Weapon object using the builder
        Weapon weapon = Weapon.weaponBuilder()
                .name(name)
                .quantity(quantity)
                .damage(damage)
                .type(type)
                .build();

        // THEN: Validate inheritance and attributes
        assertTrue(weapon instanceof Item, "The weapon should be an instance of Item (inherited).");
        assertEquals(name, weapon.getName(), "The weapon's name should match the given value.");
        assertEquals(quantity, weapon.getQuantity(), "The weapon's quantity should match the given value.");
        assertEquals(damage, weapon.getDamage(), "The weapon's damage should match the given value.");
        assertEquals(type, weapon.getType(), "The weapon's type should match the given value.");
    }

    /**
     * Tests that an exception is thrown when trying to create a Weapon
     * with negative damage. Verifies proper error handling for invalid inputs.
     *
     * @param name     the name of the weapon
     * @param quantity the quantity of the weapon
     * @param damage   the damage dealt by the weapon (invalid if negative)
     * @param type     the type of the item (e.g., "WEAPON")
     */
    @ParameterizedTest
    @CsvSource({
        "Cursed Sword, 1, -10, WEAPON"
    })
    void testNegativeDamage(String name, int quantity, int damage, Item.Type type) {
        // WHEN & THEN: Verify that an exception is thrown for negative damage
        assertThrows(IllegalArgumentException.class, () -> {
            Weapon.weaponBuilder()
                    .name(name)
                    .quantity(quantity)
                    .damage(damage)
                    .type(type)
                    .build();
        }, "Creating a weapon with negative damage should throw an IllegalArgumentException.");
    }
}
