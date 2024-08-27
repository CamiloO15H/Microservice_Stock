package stock_microservices.domain.exception;

public class NegativeNotAllowedException extends RuntimeException {
    public NegativeNotAllowedException(String message) {
        super(message);
    }
}
