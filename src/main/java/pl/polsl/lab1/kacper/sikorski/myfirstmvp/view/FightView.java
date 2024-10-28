package pl.polsl.lab1.kacper.sikorski.myfirstmvp.view;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Enemy;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Player;

/**
 * Responsible for displaying information about the fight and its results.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class FightView {

    /**
     * Constructs a FightView object. This constructor initializes a fight view
     * with default values.
     */
    public FightView() {

    }

    /**
     * Displays information about the player and the enemy before the fight.
     *
     * @param player The player.
     * @param enemy The enemy.
     */
    public void displayFight(Player player, Enemy enemy) {
        System.out.println("You are fighting " + enemy.getName() + " (" + enemy.getType() + ") with " + enemy.getHealth() + " health.");
        System.out.println("Your current health: " + player.getHealth());
    }

    /**
     * Displays the outcome of a fight turn.
     *
     * @param playerAttack The amount of damage the player dealt.
     * @param enemyAttack The amount of damage the enemy dealt.
     */
    public void displayTurnOutcome(int playerAttack, int enemyAttack) {
        System.out.println("You dealt " + playerAttack + " damage to the enemy!");
        System.out.println("The enemy dealt " + enemyAttack + " damage to you!");
    }

    /**
     * Displays the result of the battle.
     *
     * @param result The result of the fight ("victory" or "defeat").
     * @param reward The reward received by the player.
     */
    public void displayBattleResult(String result, int reward) {
        if (result.equals("victory")) {
            System.out.println("You won the fight! You earned " + reward + " gold.\n------------");
        } else {
            System.out.println("You were defeated...");
        }
    }
}
