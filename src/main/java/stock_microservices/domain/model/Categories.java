package stock_microservices.domain.model;

public class Categories { //IDENTIDAD

    private final Long id;
    private final String name;
    private final String description;

    public Categories(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

}

