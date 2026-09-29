package app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "store")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Store{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "store")
    @Builder.Default
    private List<Product> product = new ArrayList<>();

    @OneToMany (mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Offers> weeklyOffers = new ArrayList<>();
}