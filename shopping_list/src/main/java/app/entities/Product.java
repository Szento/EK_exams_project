package app.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "product")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product{
    @Id
    private Long id;
    private String name;
    private double storePrice;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}