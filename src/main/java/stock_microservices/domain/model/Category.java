package stock_microservices.domain.model;

import stock_microservices.domain.utils.DomainConstants;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Category {
    //Attributes

    private Long id;
    private String name;
    private String description;
    private List<Product> products;

    // Constructors

    public Category(Long id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        this.products = products;
    }

    public Category() {
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
