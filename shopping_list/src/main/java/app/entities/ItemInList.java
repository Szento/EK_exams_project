package app.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table (name = "item_in_list", uniqueConstraints = @UniqueConstraint(columnNames = {"shopping_list_id", "product_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder 
public class ItemInList{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notes;
    private String imagePath;
    private double pricePerItem;
    private int quantity;
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    
}