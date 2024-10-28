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
     * Scanner object to read input from the console
     */
    private Scanner scanner;

    /**
     * Indicates whether the game is currently running
     */
    private boolean gameRunning;

    /**
     * Controller responsible for handling the combat logic between the player
     * and enemies.
     */
    private FightController fightController;

    /**
     * Constructs a GameController object. Initializes the controller for
     * managing the game input/output operations. It also initializes the
     * scanner for reading input from the console and sets the player's name to
     * an empty string.
     */
    public GameController() {
        scanner = new Scanner(System.in);
        playerName = "";
    }

    /**
     * Runs the game by prompting the user for their name. This method ensures
     * that the name is valid, i.e., it must be between 3 and 10 characters. If
     * the player enters an invalid name, an exception is thrown and handled,
     * and the prompt is repeated. If the name is valid, the game will start.
     */
    public void gameRun() {
        System.out.println("Hello and welcome!");

        boolean isValidName = false;

        while (!isValidName) {
            System.out.println("Please enter your name: ");

            playerName = scanner.next();

            try {
                if (playerName.isEmpty() || playerName.length() < 3) {
                    throw new InvalidNameException("Player name must be at least 3 characters long.");
                } else if (playerName.length() > 10) {
                    throw new InvalidNameException("Player name is too long, character limit is 10");
                }
                isValidName = true;

            } catch (InvalidNameException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        player = new Player(100, playerName, "Player");
        ioController = new IOController(player);

        ioController.displayPlayer();
        ioController.displayInventory();

        gameStarted();
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

            ioController.displayPlayer();
            ioController.displayInventory();

            gameStarted();
        } catch (InvalidNameException e) {
            System.out.println("Error: " + e.getMessage());
            gameRun();
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
        fightController = new FightController();

        while (gameRunning) {
            System.out.println("1. View Player\n2. Fight Enemy\n3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ioController.displayPlayer();
                    ioController.displayInventory();
                    break;
                case 2:
                    fightController.startFight(player);
                    break;
                case 3:
                    gameRunning = false;
                    System.out.println("Exiting game...");
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
            }

            if (player.getHealth() <= 0) {
                gameRunning = false;
                break;
            }
        }
    }
}
