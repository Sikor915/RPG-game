package pl.polsl.lab1.kacper.sikorski.myfirstmvp.model;

import java.util.ArrayList;

/**
 * Represents a player in the game, extending the Entity class.
 * This class holds the player's inventory and weapons, and provides methods to access them.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class Player extends Entity 
{
   
    /** The inventory of the player. */
    private Inventory playerInventory = new Inventory();

    /** The list of weapons owned by the player. */
    private ArrayList<Weapon> weapons = new ArrayList<>();
    
    /**
     * Constructs a Player with the specified health, name, and type.
     * Initializes the player's inventory and adds a default weapon and item.
     *
     * @param health the health of the player
     * @param name   the name of the player
     * @param type   the type of the player
     */
    public Player(int health, String name, String type) 
    {
        super(health, name, type);
        Weapon shortsword = new Weapon("Shortsword", 1, "Melee", 15);
        weapons.add(shortsword);
        
        Item item = new Item("Health Potion", 2);
        playerInventory.addItem(item);
    }
    
    /**
     * Returns the player's inventory.
     *
     * @return the inventory of the player
     */
    public Inventory getPlayerInventory() 
    {
        return playerInventory;
    }
    
    /**
     * Returns the list of weapons owned by the player.
     *
     * @return the list of weapons
     */
    public ArrayList<Weapon> getWeapons() 
    {
        return weapons;
    }
}
