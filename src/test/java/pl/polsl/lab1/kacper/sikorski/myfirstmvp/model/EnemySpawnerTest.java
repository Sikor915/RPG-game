package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Tests for the EnemySpawner class.
 *
 * @author sikor
 * @version 1.0
 */
public class EnemySpawnerTest {

    @ParameterizedTest
    @CsvSource({
        "Dark Elf, 80, 40"
    })
    void testSpawnRandomEnemy() {
        // GIVEN
        EnemySpawner enemySpawner = new EnemySpawner();

        // WHEN
        Enemy enemy = enemySpawner.spawnRandomEnemy();

        // THEN
        assertNotNull(enemy, "Spawned enemy should not be null.");
        assertNotNull(enemy.getName(), "Enemy name should not be null.");
        assertTrue(enemy.getHealth() > 0, "Enemy health should be greater than 0.");
        assertTrue(enemySpawner.getRewards().contains(enemy.getReward()),
                "Enemy reward should match one of the predefined rewards.");
    }

    @ParameterizedTest
    @CsvSource({
        "Dark Elf, 80, 40",
        "Demon, 9999, 9999"
    })
    void testAddEnemyType(String name, int health, int reward) {
        // GIVEN
        EnemySpawner enemySpawner = new EnemySpawner();
        // WHEN
        enemySpawner.addEnemyType(name, health, reward);

        // THEN
        assertTrue(enemySpawner.getEnemyTypes().contains(name),
                "New enemy name should be added to the list.");
        assertTrue(enemySpawner.getHealthValues().contains(health),
                "New enemy health should be added to the list.");
        assertTrue(enemySpawner.getRewards().contains(reward),
                "New enemy reward should be added to the list.");
    }

    @ParameterizedTest
    @CsvSource({
        "Dark Elf, -10, 40"
    })
    void testAddInvalidEnemyType(String name, int health, int reward) {
        // GIVEN
        EnemySpawner enemySpawner = new EnemySpawner();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            enemySpawner.addEnemyType(name, health, reward); // Invalid health
        }, "Adding an enemy with negative health should throw an exception.");
    }

    @ParameterizedTest
    @CsvSource({
        "Dark Elf, 80, 40"
    })
    void testSpawnRandomEnemyWithNewEnemyType(String name, int health, int reward) {
        // GIVEN
        EnemySpawner enemySpawner = new EnemySpawner();
        enemySpawner.addEnemyType(name, health, reward);

        // WHEN
        boolean found = false;
        for (int i = 0; i < 100; i++) { // Retry to ensure randomness is covered
            Enemy enemy = enemySpawner.spawnRandomEnemy();
            if (enemy.getName().equals(name)) {
                found = true;

                // THEN
                assertEquals(health, enemy.getHealth(), "Health should match the newly added enemy.");
                assertEquals(reward, enemy.getReward(), "Reward should match the newly added enemy.");
                break;
            }
        }

        // THEN
        assertTrue(found, "The newly added enemy should eventually spawn.");
    }

    @ParameterizedTest
    @CsvSource({
        "Dark Elf, 80, 40"
    })
    void testSpawnEnemyWithEmptyEnemyList() {
        // GIVEN
        EnemySpawner enemySpawner = new EnemySpawner();
        enemySpawner.getEnemyTypes().clear();

        // WHEN
        Enemy enemy = enemySpawner.spawnRandomEnemy();

        // THEN
        assertNull(enemy, "No enemy should be spawned when the enemy list is empty.");
    }

}
