package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the Weapon class.
 *
 * @author sikor
 * @version 1.0
 */
public class WeaponTest {

    @ParameterizedTest
    @CsvSource({
        "Sword, 1, 15, WEAPON",
    })
    void testWeaponBuilder(String name, int quantity, int damage, Item.Type type) {
        // GIVEN-WHEN
        Weapon weapon = Weapon.weaponBuilder()
                .name(name)
                .quantity(quantity)
                .damage(damage)
                .type(type)
                .build();

        // THEN
        assertNotNull(weapon, "The weapon should be properly created.");
        assertEquals(name, weapon.getName(), "The weapon's name should be the same as wanted.");
        assertEquals(quantity, weapon.getQuantity(), "The quantity of the weapon should be equal to desired.");
        assertEquals(damage, weapon.getDamage(), "The damage of the weapon should be equal to desired.");
        assertEquals(type, weapon.getType(), "The item type should be correct with given.");
    }

    @ParameterizedTest
    @CsvSource({
        "Axe, 2, 20, WEAPON",
    })
    void testWeaponInheritanceFromItem(String name, int quantity, int damage, Item.Type type) {
        // GIVEN-WHEN
          Weapon weapon = Weapon.weaponBuilder()
                .name(name)
                .quantity(quantity)
                .damage(damage)
                .type(type)
                .build();

        // THEN
        assertTrue(weapon instanceof Item, "The weapon should be an instance of Item (inherited).");
        assertEquals("Axe", weapon.getName(), "The weapon's name should be the same as wanted.");
        assertEquals(2, weapon.getQuantity(), "The quantity of the weapon should be equal to desired.");
        assertEquals(20, weapon.getDamage(), "The damage of the weapon should be equal to desired.");
        assertEquals(Item.Type.WEAPON, weapon.getType(), "The item type should be correct with given.");
    }

    @ParameterizedTest
    @CsvSource({
        "Cursed Sword, 1, -10, WEAPON",
    })
    void testNegativeDamage(String name, int quantity, int damage, Item.Type type) {
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            // Try to build a weapon with negative damage
            Weapon.weaponBuilder()
                    .name(name)
                    .quantity(quantity)
                    .damage(damage)
                    .type(type)
                    .build();
        }, "Creating a weapon with negative damage should throw an IllegalArgumentException.");
    }
}
