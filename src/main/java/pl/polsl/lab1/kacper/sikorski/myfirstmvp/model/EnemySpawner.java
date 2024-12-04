package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import lombok.Getter;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class responsible for spawning random enemies. This class generates enemies
 * with varying health, types, and rewards that are used during the gameplay.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Getter
public class EnemySpawner {

    /**
     * ArrayList of enemy types (names) that can be spawned
     */
    private static ArrayList<String> enemyTypes;

    /**
     * Corresponding health values for the enemy types
     */
    private static ArrayList<Integer> healthValues;

    /**
     * Corresponding reward values (in gold) for defeating the enemy types
     */
    private static ArrayList<Integer> rewards;

    /**
     * Random number generator for spawning enemies
     */
    private Random random;

    /**
     * Constructor for EnemySpawner. Initializes lists of enemy types, health
     * values, and rewards for random enemy generation.
     */
    public EnemySpawner() {
        enemyTypes = new ArrayList<>();
        enemyTypes.add("Goblin");
        enemyTypes.add("Orc");
        enemyTypes.add("Troll");
        enemyTypes.add("Kobold");
        enemyTypes.add("Dragonborn");
        enemyTypes.add("Human Fighter");

        healthValues = new ArrayList<>();
        healthValues.add(50);
        healthValues.add(75);
        healthValues.add(100);
        healthValues.add(60);
        healthValues.add(120);
        healthValues.add(70);

        rewards = new ArrayList<>();
        rewards.add(10);
        rewards.add(20);
        rewards.add(30);
        rewards.add(15);
        rewards.add(50);
        rewards.add(-10);

        random = new Random();
    }

    /**
     * Spawns a random enemy with random attributes (health, name, and reward).
     * This method selects random values from predefined arrays of enemy types,
     * health, and rewards to create and return an enemy.
     *
     * @return A new Enemy object with random attributes.
     */
    public Enemy spawnRandomEnemy() {
        int index = random.nextInt(enemyTypes.size());

        // Create a new enemy using only the reward
        Enemy enemy = new Enemy(rewards.get(index));

        // Optionally set additional fields (health, name, type) if needed, as needed
        // Set the name, health, and type manually or with other setters
        enemy.setHealth(healthValues.get(index)); // You would need a setter for health.
        enemy.setName(enemyTypes.get(index));  // Set the name as the type
        enemy.setType("Enemy");  // Default type

        return enemy;
    }

    /**
     * Adds a new enemy type to the spawner with specified attributes.
     *
     * @param name The name of the new enemy type.
     * @param health The health value of the new enemy type.
     * @param reward The reward given for defeating the new enemy type.
     */
    public void addEnemyType(String name, int health, int reward) {
        enemyTypes.add(name);
        healthValues.add(health);
        rewards.add(reward);
    }
}
