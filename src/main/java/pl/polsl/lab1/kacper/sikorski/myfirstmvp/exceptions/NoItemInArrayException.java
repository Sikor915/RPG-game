package pl.polsl.lab1.kacper.sikorski.myfirstmvp.exceptions;

import lombok.*;

/**
 * Custom exception class used to handle situations when an array
 * does not contain an item. This exception is thrown for example when trying
 * to remove an item that does not exist
 *
 * @author Kacper Sikorski
 * @version 1.0
 */
@ToString
public class NoItemInArrayException extends Exception {
     /**
     * Constructor that passes the custom exception message to the Exception
     * superclass.
     *
     * @param message The error message to be displayed when the exception is
     * thrown
     */
    public NoItemInArrayException(String message) {
        super(message);
    }   
}
