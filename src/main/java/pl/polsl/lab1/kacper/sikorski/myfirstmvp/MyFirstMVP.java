package pl.polsl.lab1.kacper.sikorski.myfirstmvp;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.GUI;

/**
 * The main class for the MyFirstMVP application. This class initializes and
 * starts the game using the GameController.
 *
 * It supports running the game with or without command-line arguments.
 *
 * Accessibility options included: - Tooltip guidance for components - Error
 * handling with JOptionPane - Key binding and mnemonic for Confirm button
 *
 * @version 1.0
 */
public class MyFirstMVP {

    /**
     * The method supports two cases: 1. If no arguments are passed, the game
     * will default to empty argument (""). 2. If arguments are passed, the
     * first argument (args[0]) will be treated as the player's name.
     *
     * @param args Arguments for main function args[0] (optional) - the name of
     * the player.
     */
    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "";

        // Run the GUI with the given name argument if present
        javax.swing.SwingUtilities.invokeLater(() -> new GUI(name));
    }
}
