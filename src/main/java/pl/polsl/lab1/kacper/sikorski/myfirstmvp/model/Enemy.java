package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

/**
 * The Enemy class represents an enemy entity in the game, extending the Entity class.
 * An enemy has a specific health value, name, type, and a reward for defeating it.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class Enemy extends Entity 
{
    
    /** The reward gold the player gets after defeating the enemy */
    private int reward;
    
    /**
     * Constructor for creating an Enemy object.
     * 
     * @param health The health points of the enemy
     * @param name The name of the enemy
     * @param type The type or classification of the enemy (e.g., monster, boss)
     * @param reward The reward given to the player upon defeating the enemy
     */
    public Enemy(int health, String name, String type, int reward) 
    {
        super(health, name, type);
        this.reward = reward;
    }
    
    /**
     * Gets the reward the player receives after defeating the enemy.
     * 
     * @return The reward value.
     */
    public int getReward()
    {
        return reward;
    }
}
