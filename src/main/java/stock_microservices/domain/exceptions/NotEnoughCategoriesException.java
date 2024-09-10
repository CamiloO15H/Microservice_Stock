package stock_microservices.domain.exceptions;

public class NotEnoughCategoriesException extends RuntimeException {
    public NotEnoughCategoriesException(String message) {
        super(message);
    }
}
