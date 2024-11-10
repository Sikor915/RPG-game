package pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller;

import java.util.Scanner;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.view.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.model.*;
import pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions.InvalidNameException;

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
public class GameController {

    /**
     * Controller responsible for managing input/output operations
     */
    private IOController ioController;

    /**
     * The name of the player
     */
    private String playerName;

    /**
     * The player object representing the user in the game
     */
    private Player player;

    /**
     * Indicates whether the game is currently running
     */
    private boolean gameRunning;

    /**
     * Controller responsible for handling the combat logic between the player
     * and enemies.
     */
    private FightController fightController;

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
        try {
            playerName = name;
            if (playerName.isEmpty() || playerName.length() < 3) {
                throw new InvalidNameException("Player name must be at least 3 characters long.");
            } else if (playerName.length() > 10) {
                throw new InvalidNameException("Player name is too long, character limit is 10");
            }

            player = new Player(100, playerName, "Player");
            ioController = new IOController(player);
            gameStarted();
        } catch (InvalidNameException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Handles the main game loop once the game has started. The method
     * continuously prompts the player with choices to view their player info,
     * fight an enemy, or exit the game. The loop runs until the player chooses
     * to exit or dies.
     */
    public void gameStarted() {
        gameRunning = true;
        gameWindow.initializeUI();
        fightController = new FightController(gameWindow);
    }

    public FightController getFightController() {
        return fightController;
    }

    public Player getPlayer() {
        return player;
    }
}
