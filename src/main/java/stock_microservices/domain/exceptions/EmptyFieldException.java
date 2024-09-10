package stock_microservices.domain.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {
        super("Empty '" + message + "' field not allowed");
    }
}
