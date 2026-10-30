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
@Table (name = "users") // "user" is a reserved word in prostgres
@Getter @Setter @NoArgsConstructor  @AllArgsConstructor @Builder 
public class User {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column (unique = true)
    private String email;
    private String phoneNumber;

    /*cascade = CascadeType.ALL, says that if i do something with a parent,
    then it should also affect the child. The parent (user) gets deleted,
    then all its lists and its login, should be deleted as well. 
    */
    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Authenticator authenticator;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ShoppingList> shoppingLists = new ArrayList<>();
}