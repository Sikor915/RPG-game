package pl.polsl.lab1.kacper.sikorski.myfirstmvp.view;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.Weapon;

/**
 * Represents the display information for a weapon in the game. This class is
 * responsible for rendering the weapon's details to the user.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
public class WeaponDisplayInfo {

    /**
     * Test weapon to be displayed.
     */
    private Weapon weapon;

    /**
     * Constructs a WeaponDisplayInfo object. Initializes the display for weapon
     * information with a default test weapon.
     */
    public WeaponDisplayInfo() {
        weapon = new Weapon("Shortsword", 1, "Melee", 15);
    }
}
