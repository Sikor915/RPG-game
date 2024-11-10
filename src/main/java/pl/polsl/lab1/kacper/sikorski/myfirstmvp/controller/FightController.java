package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Enemy;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Player;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.EnemySpawner;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.GameWindow;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
import java.util.Random;

/**
 * Handles the combat mechanics between the player and enemies.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
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

        // Display initial enemy information on GameWindow
        gameWindow.updateEnemyInfo(enemy.getName(), enemy.getHealth());

        // Choose the player's weapon (for simplicity, using the first weapon)
        chosenWeapon = player.getWeapons().get(0);
        int playerAttack = chosenWeapon.getDamage();

        // Combat loop
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            int enemyAttack = random.nextInt(15) + 1;

            // Apply damage to enemy and player
            enemy.setHealth(enemy.getHealth() - playerAttack);
            player.setHealth(player.getHealth() - enemyAttack);

            // Update GUI after each turn
            gameWindow.updatePlayerHealth();
            gameWindow.updateEnemyInfo(enemy.getName(), enemy.getHealth());

            // Display outcome after battle
            if (enemy.getHealth() <= 0 && player.getHealth() > 0) {
                gameWindow.displayBattleResult("Victory", enemy.getReward());
                Item gold = new Item("Gold", enemy.getReward());
                player.getPlayerInventory().addItem(gold);
                break;
            }

            if (player.getHealth() <= 0) {
                gameWindow.displayBattleResult("Defeat", 0);
                break;
            }
        }
    }
}
