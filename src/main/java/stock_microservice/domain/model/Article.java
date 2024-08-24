package stock_microservice.domain.model;

public class Article {

    private Long id;
    private int amount;
    private Double price;
    private Long id_category;
    private Long id_marca;

    public Article(Long id, int amount, Double price, Long id_category, Long id_marca) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.id_category = id_category;
        this.id_marca = id_marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public Long getId_marca() {
        return id_marca;
    }

    public void setId_marca(Long id_marca) {
        this.id_marca = id_marca;
    }
}
