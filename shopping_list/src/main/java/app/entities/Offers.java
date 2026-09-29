package app.entities;

import java.time.LocalDate;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "offers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Offers{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private double offerPrice;
    private double percentage;
    private LocalDate endDate;



    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}