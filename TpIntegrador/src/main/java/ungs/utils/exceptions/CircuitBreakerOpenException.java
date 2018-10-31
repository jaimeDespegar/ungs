package ungs.utils.exceptions;

public class CircuitBreakerOpenException extends RuntimeException {

    public CircuitBreakerOpenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircuitBreakerOpenException(String message) {
        super(message);
    }
}