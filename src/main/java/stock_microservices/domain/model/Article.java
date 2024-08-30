package stock_microservices.domain.model;

import java.util.Set;

public class Article {

    private final Long id;
    private final String name;
    private final String description;
    private final int amount;
    private final Double price;
    private Brand brand;
    private Set<Categories> categories; // Cambiado a Set para manejar múltiples categorías

    public Article(Long id, String name, String description, int amount, Double price, Brand brand, Set<Categories> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Double getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public Set<Categories> getCategories() { // Devuelve un Set de categorías
        return categories;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCategories(Set<Categories> categories) { // Permite asignar un Set de categorías
        this.categories = categories;
    }
}
