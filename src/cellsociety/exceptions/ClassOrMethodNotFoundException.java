package cellsociety.exceptions;

/**
 * custom exception for class or method not found in reflection
 */

public class ClassOrMethodNotFoundException extends RuntimeException{
  public ClassOrMethodNotFoundException(String message){
    super(message);
  }

  public ClassOrMethodNotFoundException(String message, Throwable cause){
    super(message,cause);
  }
}
