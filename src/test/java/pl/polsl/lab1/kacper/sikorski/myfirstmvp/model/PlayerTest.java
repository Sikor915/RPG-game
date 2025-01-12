package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for the Player class.
 * Verifies the correct initialization and validation of player attributes,
 * inventory, and weapons.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class PlayerTest {

    /**
     * Tests the proper initialization of a Player object with valid parameters.
     * Verifies health, name, type, inventory, and default weapons.
     *
     * @param health the initial health of the player
     * @param name   the name of the player
     * @param type   the type of the player (e.g., "Player")
     */
    @ParameterizedTest
    @CsvSource({
        "100, Hero, Player"
    })
    void testPlayerInitialization(int health, String name, String type) {
        // GIVEN: A new Player object with valid parameters
        Player player = new Player(health, name, type);

        // WHEN: Retrieving the player's inventory and attributes
        Inventory inventory = player.getPlayerInventory();

        // THEN: Validate player's health, name, and type
        assertEquals(health, player.getHealth(), "Player health should be initialized correctly.");
        assertEquals(name, player.getName(), "Player name should be initialized correctly.");
        assertEquals(type, player.getType(), "Player type should be initialized correctly.");

        // Validate the inventory initialization
        assertNotNull(inventory, "Player inventory should be initialized.");
        assertEquals(4, inventory.getItems().size(), "Player inventory should contain 4 items.");
        assertNotEquals(12, inventory.getItems().size(), "Player inventory should not contain this many items at the start.");

        // Validate specific items in the inventory
        assertNotNull(inventory.getItemByName("Healing Potion"), "Healing Potion should be in the inventory.");
        assertEquals(2, inventory.getItemByName("Healing Potion").getQuantity(), "Healing Potion should have correct quantity.");
        assertNotNull(inventory.getItemByName("DEV ITEM"), "DEV ITEM should be in the inventory.");
        assertEquals(124, inventory.getItemByName("DEV ITEM").getQuantity(), "DEV ITEM should have correct quantity.");

        // Validate default weapons
        assertNotNull(player.getWeapons(), "Player weapons list should be initialized.");
        assertEquals(1, player.getWeapons().size(), "Player should have one weapon by default.");
        assertEquals("Shortsword", player.getWeapons().get(0).getName(), "Default weapon should be a Shortsword.");
        assertEquals(15, player.getWeapons().get(0).getDamage(), "Default weapon should have correct damage.");
    }

    /**
     * Tests the initialization of a Player object with invalid parameters.
     * Ensures that an IllegalArgumentException is thrown for each invalid parameter set.
     *
     * @param health the initial health of the player (invalid if negative)
     * @param name   the name of the player (invalid if null or empty)
     * @param type   the type of the player (invalid if null or empty)
     */
    @ParameterizedTest
    @CsvSource({
        "-5, Hero, Player",   // Invalid health
        "100, , Player",      // Invalid name (empty)
        "100, Hero, "         // Invalid type (empty)
    })
    void testInvalidPlayerInitialization(int health, String name, String type) {
        // WHEN & THEN: Verify that an exception is thrown for invalid parameters
        assertThrows(IllegalArgumentException.class, () -> new Player(health, name, type),
                "Invalid player parameters should throw an IllegalArgumentException.");
    }
}
