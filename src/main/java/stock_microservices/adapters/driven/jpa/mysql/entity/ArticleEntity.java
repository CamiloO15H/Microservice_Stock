package stock_microservices.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "id_categories")
    private Long idCategories;

    @Column(name = "id_brand")
    private Long idBrand;

    // Si la relación es bidireccional, puede ser necesario usar @ManyToMany o @OneToMany en lugar de @Column
    // @ManyToMany
    // @JoinTable(
    //     name = "Articulo_Categoria",
    //     joinColumns = @JoinColumn(name = "articulo_id"),
    //     inverseJoinColumns = @JoinColumn(name = "categoria_id")
    // )
    // private Set<CategoryEntity> categories;
}
