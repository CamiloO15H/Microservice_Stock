package stock_microservices.adapters.driven.jpa.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "brand")
public class BrandEntity {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = 127)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<ProductEntity> products;
}
