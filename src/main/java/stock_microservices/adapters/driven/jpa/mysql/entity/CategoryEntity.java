package stock_microservices.adapters.driven.jpa.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class CategoryEntity {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = 127)
    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<ProductEntity> products;
}
