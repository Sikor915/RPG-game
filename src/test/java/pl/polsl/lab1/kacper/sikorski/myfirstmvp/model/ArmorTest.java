package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for the Armor class
 *
 * @author sikor
 * @version 1.0
 */
public class ArmorTest {

    @ParameterizedTest
    @CsvSource({
        "Knight's Armor, 5, 5",
        "Mage's Robe, 3, 3",
        "Leather Vest, 0, 0",
        "Cursed Armor, -5, -5",
        "Ultra Cursed Armor 5000, -9999, -9999"
    })
    void testArmorDamageReduction(String name, int damageReduction, int expectedReduction) {
        // GIVEN
        Armor armor = Armor.armorBuilder()
                .name(name)
                .quantity(1)
                .type(Item.Type.ARMOR)
                .damageReduction(damageReduction)
                .build();

        // WHEN
        int actualDamageReduction = armor.getDamageReduction();

        // THEN
        assertEquals(expectedReduction, actualDamageReduction, "Armor reduction should match expected value.");
    }
}
