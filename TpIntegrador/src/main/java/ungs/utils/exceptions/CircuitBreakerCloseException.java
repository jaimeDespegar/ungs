package ungs.utils.exceptions;

public class CircuitBreakerCloseException extends RuntimeException {

    public CircuitBreakerCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircuitBreakerCloseException(String message) {
        super(message);
    }
}