package pl.polsl.lab1.kacper.sikorski.myfirstmvp.view;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Player;

/**
 * Represents the display for a player in the game. This class is responsible
 * for rendering player information and inventory to the user.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class PlayerDisplay {

    /**
     * Constructs a PlayerDisplay object. This constructor initializes the
     * display for player information.
     */
    public PlayerDisplay() {

    }

    /**
     * Displays the player's name and health to the console.
     *
     * @param player the player whose information is to be displayed
     */
    public void displayPlayer(Player player) {
        System.out.println("Player name: " + player.getName() + ", Health: " + player.getHealth());
    }

    /**
     * Displays the player's inventory, including items and weapons.
     *
     * @param player the player whose inventory is to be displayed
     */
    public void displayPlayerInventory(Player player) {
        var playerItems = player.getPlayerInventory().getItems();
        var playerWeapons = player.getWeapons();

        System.out.println("---Inventory--- ");

        for (var item : playerItems) {
            System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity());
        }

        for (var weapon : playerWeapons) {
            System.out.println("Name: " + weapon.getName() + ", Type: " + weapon.getType() + ", Damage: " + weapon.getDamage() + ", Quantity: " + weapon.getQuantity());
        }
    }
}
