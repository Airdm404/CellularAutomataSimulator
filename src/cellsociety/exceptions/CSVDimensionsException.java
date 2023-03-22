package cellsociety.exceptions;

/**
 * custom exception for incorrect CSV dimension
 */

public class CSVDimensionsException extends RuntimeException{
    public CSVDimensionsException(String message){
        super(message);
    }

    public CSVDimensionsException(String message, Throwable cause){
        super(message,cause);
    }
}
