package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Enemy;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Player;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.EnemySpawner;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.FightView;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
import java.util.Random;

/**
 * Handles the combat mechanics between the player and enemies.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class FightController 
{
    
    /** View class responsible for displaying fight details */
    private FightView fightView;

    /** Class responsible for spawning enemies */
    private EnemySpawner enemySpawner;

    /** Random number generator for enemy attacks */
    private Random random;

    /** The weapon chosen by the player for the fight */
    private Weapon chosenWeapon;

    /**
     * Constructor to initialize the FightController with necessary components.
     * It sets up the view for displaying the fight and initializes the random
     * generator and enemy spawner.
     */
    public FightController() 
    {
        fightView = new FightView();
        enemySpawner = new EnemySpawner();
        random = new Random();
    }

    /**
     * Starts a fight between the player and a randomly spawned enemy.
     * This method continues the fight until either the player or the enemy 
     * is defeated, and updates the player's inventory if the player wins.
     * 
     * @param player The player participating in the fight.
     */
    public void startFight(Player player) 
    {
        Enemy enemy = enemySpawner.spawnRandomEnemy();
        fightView.displayFight(player, enemy);
        chosenWeapon = player.getWeapons().get(0);
        int playerAttack = chosenWeapon.getDamage();  
        
        while (player.getHealth() > 0 && enemy.getHealth() > 0) 
        {
            int enemyAttack = random.nextInt(15) + 1; 
            
            enemy.setHealth(enemy.getHealth() - playerAttack);
            player.setHealth(player.getHealth() - enemyAttack);
            
            fightView.displayTurnOutcome(playerAttack, enemyAttack);
            
            if (enemy.getHealth() <= 0 && player.getHealth() > 0) 
            {
                fightView.displayBattleResult("victory", enemy.getReward());
                Item gold = new Item("Gold", enemy.getReward());
                player.getPlayerInventory().addItem(gold);
                break;
            }
            
            if (player.getHealth() <= 0) 
            {
                fightView.displayBattleResult("defeat", 0);
                break;
            }
        }
    }
}
