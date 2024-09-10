package stock_microservices.domain.utils;



import stock_microservices.domain.exceptions.EmptyFieldException;
import stock_microservices.domain.exceptions.NegativeNotAllowedException;
import stock_microservices.domain.exceptions.OutOfBoundsException;

import java.math.BigDecimal;

public class ValidationUtils {
    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateName(String name, int limit) throws EmptyFieldException, OutOfBoundsException {
        if (name == null || name.trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        if (name.trim().length() > limit)
            throw new OutOfBoundsException(DomainConstants.Field.NAME + " " + limit + " " + DomainConstants.CHARS_LIMIT_REACHED_MESSAGE);
    }

    public static void validateDescription(String description, int limit) throws EmptyFieldException, OutOfBoundsException {
        if(description == null || description.trim().isEmpty()) throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        if(description.trim().length() > limit)
            throw new OutOfBoundsException(DomainConstants.Field.DESCRIPTION + " " + limit + " " + DomainConstants.CHARS_LIMIT_REACHED_MESSAGE);

    }

    public static void validatePrice(BigDecimal price) throws EmptyFieldException, OutOfBoundsException {
        if(price == null) throw new EmptyFieldException(DomainConstants.Field.PRICE.toString());
        if(price.compareTo(BigDecimal.valueOf(0)) < 0)
            throw new NegativeNotAllowedException(DomainConstants.NEGATIVE_PRICE_MESSAGE);

    }

    public static void validateQuantity(Long quantity) throws EmptyFieldException, OutOfBoundsException {
        if(quantity == null) throw new EmptyFieldException(DomainConstants.Field.QUANTITY.toString());
        if(quantity < 0)
            throw new NegativeNotAllowedException(DomainConstants.NEGATIVE_QUANTITY_MESSAGE);

    }
}
