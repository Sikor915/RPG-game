package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Enemy;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Player;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.EnemySpawner;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.GameWindow;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
import java.util.Random;
import lombok.*;

/**
 * Handles the combat mechanics between the player and enemies.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Data
public class FightController {

    /**
     * View class responsible for displaying fight details
     */
    private GameWindow gameWindow;

    /**
     * Class responsible for spawning enemies
     */
    private EnemySpawner enemySpawner;

    /**
     * Random number generator for enemy attacks
     */
    private Random random;

    /**
     * The weapon chosen by the player for the fight
     */
    private Weapon chosenWeapon;

    /**
     * Constructor to initialize the FightController with necessary components.
     * It sets up the view for displaying the fight and initializes the random
     * generator and enemy spawner.
     *
     * @param gameWindow The GameWindow for displaying and handling
     * interactions.
     */
    public FightController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        enemySpawner = new EnemySpawner();
        random = new Random();
    }

    /**
     * Starts a fight between the player and a randomly spawned enemy. Updates
     * the GUI with the enemy's name and health, and continues the fight until
     * either the player or the enemy is defeated.
     *
     * @param player The player participating in the fight.
     */
    public void startFight(Player player) {
        Enemy enemy = enemySpawner.spawnRandomEnemy();

        // Notify the GameWindow to display enemy information
        gameWindow.updateEnemyInfo(enemy.getName(), enemy.getHealth());

        chosenWeapon = player.getWeapons().get(0);

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {

            gameWindow.displayBattleOptions();

            // Get player's action from the GUI
            String action = gameWindow.getPlayerAction();

            if ("Attack".equals(action)) {
                // Attack logic
                enemy.setHealth(enemy.getHealth() - chosenWeapon.damage());
                gameWindow.setPlayerAction("");
            } else if ("Use Item".equals(action)) {

                boolean itemUsed = player.getPlayerInventory().useHealingItem(player);
                if (itemUsed) {
                    gameWindow.displayMessage("You used a Healing Potion and restored 20 health");
                    gameWindow.updatePlayerHealth();
                } else {
                    gameWindow.displayMessage("You don't have any Healing Potions!");
                }
                gameWindow.setPlayerAction("");
            }

            if (enemy.getHealth() > 0) {
                int enemyAttack = random.nextInt(15) + 1;
                player.setHealth(player.getHealth() - enemyAttack);
            }

            // Update GameWindow after each turn
            gameWindow.updatePlayerHealth();
            gameWindow.updateEnemyInfo(enemy.getName(), enemy.getHealth());

            if (enemy.getHealth() <= 0) {
                gameWindow.displayBattleResult("Victory", enemy.getReward());
                player.getPlayerInventory().addItem(new Item("Gold", enemy.getReward()));
                int potionDrop = random.nextInt(20) + 1;
                int potionCount = random.nextInt(3) + 1;
                if (potionDrop >= 8) {
                    player.getPlayerInventory().addItem(new Item("Healing Potion", potionCount));
                    gameWindow.displayMessage("You have acquired " + potionCount + " healing potions.");
                }
                break;
            }

            if (player.getHealth() <= 0) {
                gameWindow.displayBattleResult("Defeat", 0);
                break;
            }
        }
    }
}
