package app.dao;

import app.config.HibernateConfig;
import app.dto.ShoppingListDTO;
import app.entities.ShoppingList;
import app.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListDAOImplTest {
    private static EntityManagerFactory emf;
    private static ShoppingListDAO shoppingListDAO;
    private static UserDAO userDAO;

    private User user;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactory();
        shoppingListDAO = new ShoppingListDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @BeforeEach
    void setUp() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM ItemInList").executeUpdate();
            em.createQuery("DELETE FROM ShoppingList").executeUpdate();
            em.createQuery("DELETE FROM Authenticator").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.getTransaction().commit();
        }

        user = userDAO.save(User.builder()
                .firstName("Test")
                .lastName("User")
                .email("test@test.dk")
                .phoneNumber("12345678")
                .build());
    }

    @Test
    @DisplayName("Given a new shopping list, when it is saved, then add an id")
    void saveGivesId() {
        ShoppingList list = ShoppingList.builder()
                .name("Casual")
                .overallPrice(12.5)
                .user(user)
                .build();

        ShoppingList saved = shoppingListDAO.save(list);

        assertNotNull(saved.getId());
        assertEquals("Casual", saved.getName());
    }

    @Test
    @DisplayName("Given a saved list, when i look it up by its id, then i should get the list back.")
    void findByIdReturnsList() {
        ShoppingList saved = shoppingListDAO.save(ShoppingList.builder()
                .name("Casual")
                .overallPrice(12.5)
                .user(user)
                .build());

        Optional<ShoppingList> result = shoppingListDAO.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals("Casual", result.get().getName());
    }

    @Test
    @DisplayName("Given multiple saved lists, when i ask for the lists, then i should get every list back")
    void findAllReturnsAllLists() {
        shoppingListDAO.save(ShoppingList.builder()
                .name("Casual")
                .overallPrice(12.5)
                .user(user)
                .build());

        shoppingListDAO.save(ShoppingList.builder()
                .name("Mealprep")
                .overallPrice(130.25)
                .user(user)
                .build());

        List<ShoppingList> result = shoppingListDAO.findAll();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Given a saved list, when i change the name and update it, then the database has the new name.")
    void updateSavedList(){
        ShoppingList saved = shoppingListDAO.save(ShoppingList.builder()
                .name("Mealprep")
                .overallPrice(120.99)
                .user(user)
                .build());

        saved.setName("Casual");
        shoppingListDAO.update(saved);

        ShoppingList fromDb = shoppingListDAO.findById(saved.getId()).orElseThrow();
        assertEquals("Casual", fromDb.getName());
    }

    @Test
    @DisplayName("Given a saved list, when it is deleted, then it can no longer be found")
    void deleteSavedList(){
        ShoppingList saved = shoppingListDAO.save(ShoppingList.builder()
                .name("Mealprep")
                .overallPrice(50)
                .user(user)
                .build());

        shoppingListDAO.delete(saved.getId());

        assertTrue(shoppingListDAO.findById(saved.getId()).isEmpty());
    }

}
