package pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions;

/**
 * Custom exception class used to handle invalid player names.
 * This exception is thrown when a player name does not meet the validation criteria.
 * 
 * @author Kacper Sikorski
 * @version 1.0
 */
public class InvalidNameException extends Exception 
{
    
    /**
     * Constructor that passes the custom exception message to the Exception superclass.
     * 
     * @param message The error message to be displayed when the exception is thrown
     */
    public InvalidNameException(String message) 
    {
        super(message);
    }
}
