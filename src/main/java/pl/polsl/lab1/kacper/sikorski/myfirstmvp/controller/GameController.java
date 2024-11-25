package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
import lombok.*;

/**
 * GameController class that handles game flow logic. It manages the player's
 * input and initializes the game elements.
 *
 * This class controls the flow of the game by asking for the player's name,
 * handling exceptions for invalid names, and displaying player information.
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@Getter
@Setter
public class GameController {

    /**
     * The name of the player
     */
    private String playerName;

    /**
     * The player object representing the user in the game
     */
    private Player player;

    /**
     * Controller responsible for handling the combat logic between the player
     * and enemies.
     */
    private FightController fightController;

    /**
     * Game window in which the game will be played.
     */
    private GameWindow gameWindow;

    /**
     * Constructs a GameController object. Initializes the controller for
     * managing the game input/output operations. It also initializes the
     * scanner for reading input from the console and sets the player's name to
     * an empty string.
     */
    public GameController() {
        playerName = "";
        this.gameWindow = new GameWindow(this);
    }

    /**
     * Runs the game with the name provided as a method argument. This method is
     * used when the player's name is passed from the command-line arguments. If
     * the name is invalid, it throws an exception and reruns the game with
     * manual input. If the name is valid, the game will start.
     *
     * @param name The name of the player passed as an argument
     */
    public void gameRun(String name) {

        playerName = name;
        player = new Player(100, playerName, "Player");
        gameStarted();
    }

    /**
     * Handles the main game loop once the game has started. The method
     * initializes the game window and fight controller. to exit or dies.
     */
    public void gameStarted() {
        gameWindow.initializeUI();
        fightController = new FightController(gameWindow);
    }

    /**
     * Returns the fight controller
     *
     * @return Fight controller
     */
    public FightController getFightController() {
        return fightController;
    }

    /**
     * Return the player that is playing
     *
     * @return Current player.
     */
    public Player getPlayer() {
        return player;
    }
}
