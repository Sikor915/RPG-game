package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the Player class.
 *
 * @author sikor
 * @version 1.0
 */
public class PlayerTest {

    @ParameterizedTest
    @CsvSource({
        "100, Hero, Player"
    })
    void testPlayerInitialization(int health, String name, String type) {
        // GIVEN
        Player player = new Player(health, name, type);

        // WHEN
        Inventory inventory = player.getPlayerInventory();

        // THEN
        assertEquals(health, player.getHealth(), "Player health should be initialized correctly.");
        assertEquals(name, player.getName(), "Player name should be initialized correctly.");
        assertEquals(type, player.getType(), "Player type should be initialized correctly.");

        assertNotNull(inventory, "Player inventory should be initialized.");
        assertEquals(4, inventory.getItems().size(), "Player inventory should contain 4 items.");
        assertNotEquals(12, inventory.getItems().size(), "Player inventory should not contain this many items at the start.");

        assertNotNull(inventory.getItemByName("Healing Potion"), "Healing Potion should be in the inventory.");
        assertEquals(2, inventory.getItemByName("Healing Potion").getQuantity(), "Healing Potion should have correct quantity.");
        assertNotNull(inventory.getItemByName("DEV ITEM"), "DEV ITEM should be in the inventory.");
        assertEquals(124, inventory.getItemByName("DEV ITEM").getQuantity(), "DEV ITEM should have correct quantity.");

        assertNotNull(player.getWeapons(), "Player weapons list should be initialized.");
        assertEquals(1, player.getWeapons().size(), "Player should have one weapon by default.");
        assertEquals("Shortsword", player.getWeapons().get(0).getName(), "Default weapon should be a Shortsword.");
        assertEquals(15, player.getWeapons().get(0).getDamage(), "Default weapon should have correct damage.");
    }

    @ParameterizedTest
    @CsvSource({
        "-5, Hero, Player",
        "100, , Player",
        "100, Hero, "
    })
    void testInvalidPlayerInitialization(int health, String name, String type) {
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> new Player(health, name, type));
    }
}
