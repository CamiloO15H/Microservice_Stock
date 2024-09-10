package stock_microservices.domain.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
        PRICE,
        QUANTITY,
        CATEGORIES,
        PRODUCTS,
        PRODUCT,
        BRAND
    }

    // NULL Messages
    public static final String FIELD_ID_NULL_MESSAGE = "Field 'id' cannot be null";
    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_PRICE_NULL_MESSAGE = "Field 'price' cannot be null";
    public static final String FIELD_QUANTITY_NULL_MESSAGE = "Field 'quantity' cannot be null";
    public static final String FIELD_CATEGORIES_NULL_MESSAGE = "Field 'categories' cannot be null";
    public static final String FIELD_BRAND_NULL_MESSAGE = "Field 'brand' cannot be null";

    // bound limits
    public static final Integer FIELD_CATEGORIES_LIMIT = 3;
    public static final Integer NAME_LENGTH_LIMIT = 50;
    public static final Integer CATEGORY_DESCRIPTION_LENGTH_LIMIT = 90;
    public static final Integer BRAND_DESCRIPTION_LENGTH_LIMIT = 120;

    // Out of bounds
    public static final String CHARS_LIMIT_REACHED_MESSAGE = "chars limit reached";
    public static final String CATEGORIES_LIMIT_REACHED_MESSAGE = "categories limit of " + FIELD_CATEGORIES_LIMIT + " reached";
    public static final String NEGATIVE_PRICE_MESSAGE = "Price cannot be negative";
    public static final String NEGATIVE_QUANTITY_MESSAGE = "Quantity cannot be negative";

    // Duplicated message
    public static final String PRODUCT_CATEGORY_DUPLICATED_MESSAGE = "Product has duplicated categories";
    public static final String PRODUCT_CATEGORY_INSUFFICIENT_MESSAGE = "Product must have at least one category";

    // Pages
    public static final Integer PAGE_SIZE = 10;


}
