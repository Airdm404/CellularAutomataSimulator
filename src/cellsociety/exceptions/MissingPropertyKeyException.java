package cellsociety.exceptions;

/**
 * custom exceptions for missing property keys in property files
 */

public class MissingPropertyKeyException extends RuntimeException {

    public MissingPropertyKeyException(String message){
        super(message);
    }

    public MissingPropertyKeyException(String message, Throwable cause){
        super(message,cause);
    }

}
