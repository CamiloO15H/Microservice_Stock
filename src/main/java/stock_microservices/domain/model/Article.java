package stock_microservices.domain.model;

public class Article {

    private final Long id;
    private final String name;
    private final String description;
    private final int amount;
    private final Double price;
    private Long idCategories;
    private Long idBrand;

    public Article(Long id, String name, String description, int amount, Double price, Long idCategories, Long idBrand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.idCategories = idCategories;
        this.idBrand = idBrand;
    }

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

    public int getAmount() {return amount;}

    public Double getPrice() {return price;}

    public Long getIdCategories() {return idCategories;}

    public void setIdCategories(Long idCategories) {this.idCategories = idCategories;}

    public Long getIdBrand() {return idBrand;}

    public void setIdBrand(Long idBrand) {this.idBrand = idBrand;}
}



