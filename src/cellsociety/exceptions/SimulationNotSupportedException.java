package cellsociety.exceptions;

/**
 * custom exception for simulation founded not supported by the project
 */

public class SimulationNotSupportedException extends RuntimeException{

    public SimulationNotSupportedException(String message){
        super(message);
    }

    public SimulationNotSupportedException(String message, Throwable cause){
        super(message,cause);
    }

}
