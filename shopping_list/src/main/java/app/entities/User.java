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
@Table (name = "users") // "user" er et reserveret ord i Postgres
@Getter @Setter @NoArgsConstructor  @AllArgsConstructor @Builder 
public class User {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column (unique = true)
    private String email;
    private String phoneNumber;

    /*cascade = CascadeType.ALL, betyder at hvis jeg gør noget ved en parent,
    skal det også gøres ved et child. altså vores parent som er user hvis det slettes,
    skal alle listerne og login slettes. */
    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Authenticator authenticator;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ShoppingList> shoppingLists = new ArrayList<>();
}