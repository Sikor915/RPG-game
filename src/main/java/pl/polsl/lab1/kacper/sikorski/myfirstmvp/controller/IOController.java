package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
/**
 * The IOController class handles input/output operations 
 * for displaying various game objects like players, enemies, and inventory.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class IOController 
{
    
    /** Player object representing the current player */
    public Player player;
    
    /**
     * Constructor for IOController.
     * Initializes the controller with the given player.
     * 
     * @param player The player object used in the controller for displaying data
     */
    public IOController(Player player)
    {
        this.player = player;
    }
    
    /**
     * Displays the given weapon by calling the WeaponDisplayInfo view.
     * 
     * @param weapon The weapon object to be displayed
     */
    public void displayWeapon(Weapon weapon)
    {
            
    WeaponDisplayInfo weaponViewController = new WeaponDisplayInfo();
        
    weaponViewController.displayWeapon();
        
    }
        
    /**
     * Displays the player details by calling the PlayerDisplay view.
     */
    public void displayPlayer()
    {
            
    PlayerDisplay playerViewController = new PlayerDisplay();
        
    playerViewController.displayPlayer(player);

    }
    
    /**
     * Displays the player's inventory by calling the PlayerDisplay view.
     */
    public void displayInventory()
    {
        PlayerDisplay playerViewController = new PlayerDisplay();
        
        playerViewController.displayPlayerInventory(player);
    }
        
}
