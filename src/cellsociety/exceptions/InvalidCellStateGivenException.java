package cellsociety.exceptions;

import java.security.spec.ECFieldF2m;

/**
 * custom exception for invalid cell state
 */

public class InvalidCellStateGivenException extends Exception {
    public InvalidCellStateGivenException(String message){
        super(message);
    }

    public InvalidCellStateGivenException(String message, Throwable cause){
        super(message,cause);
    }
}
