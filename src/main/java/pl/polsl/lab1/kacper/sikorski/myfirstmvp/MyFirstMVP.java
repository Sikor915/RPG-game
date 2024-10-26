package pl.polsl.lab1.kacper.sikorski.myfirstmvp;

import pl.polsl.lab1.kacper.sikorski.myfirstmvp.controller.GameController;
/**
 * he main class for the MyFirstMVP application.
 * This class initializes and starts the game using the GameController.
 * 
 * It supports running the game with or without command-line arguments.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class MyFirstMVP 
{
/**
 *  The method supports two cases:
 * 1. If no arguments are passed, the game will prompt the player for input via the console.
 * 2. If one argument is passed (args[0]), the argument will be treated as the player's name
 * 
 *  @param args Arguments for main function   
 *         args[0] (optional) - the name of the player.
 */
    public static void main(String[] args) 
    {
        GameController game = new GameController();
        
        if (args.length == 0) 
        {
            game.gameRun();   
        }
        else
        {
            game.gameRun(args[0]);
            
        }
    }
}
