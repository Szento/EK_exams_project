package app.routes;

import app.api.APIReader;
import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.controllers.FoodWasteController;
import app.controllers.ShoppingListController;
import app.dao.ShoppingListDAO;
import app.dao.ShoppingListDAOImpl;
import app.dao.UserDAO;
import app.dao.UserDAOImpl;
import app.dto.ShoppingListDTO;
import app.entities.ShoppingList;
import app.entities.User;
import app.services.SallingService;
import io.javalin.Javalin;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ShoppingListRoutesTest {
    private static Javalin app;
    private static EntityManagerFactory emf;
    private static ShoppingListDAO shoppingListDAO;
    private static UserDAO userDAO;
    private User user;

    @BeforeAll
    static void setUpAll(){
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactory();
        shoppingListDAO = new ShoppingListDAOImpl();
        userDAO = new UserDAOImpl();

        FoodWasteController foodWasteController = new FoodWasteController(new SallingService("test-key", new APIReader()));
        ShoppingListController shoppingListController = new ShoppingListController(shoppingListDAO, userDAO);
        Routes routes = new Routes(foodWasteController, shoppingListController);

        app = ApplicationConfig.createServer(routes.getRoutes());
        app.start(7777);

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7777;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    static void tearDownAll(){
        app.stop();
    }

    @BeforeEach
    void setUp(){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createQuery("DELETE FROM ItemInList").executeUpdate();
            em.createQuery("DELETE FROM ShoppingList").executeUpdate();
            em.createQuery("DELETE FROM Authenticator").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.getTransaction().commit();
        }
        user = userDAO.save(User.builder()
                .firstName("Tester")
                .lastName("Tester")
                .email("tester@test.dk")
                .phoneNumber("12345678")
                .build());
    }

    @Test
    @DisplayName("Given two save lists, when i get /shoppinglist, then i get status 200 and both lists")
    void getAllReturnedLists(){
        shoppingListDAO.save(ShoppingList.builder()
                .name("Casual")
                .overallPrice(34.5)
                .user(user)
                .build());
        shoppingListDAO.save(ShoppingList.builder()
                .name("Mealprep")
                .overallPrice(143.7)
                .user(user)
                .build());


        when().
                get("/shoppinglists").
        then().
                statusCode(200)
                .body("size()", equalTo(2))
                .body("name", hasItems("Casual", "Mealprep"));
    }

    @Test
    @DisplayName("Given no list with the id 999, when i ask for it, then i get status 404 and an error message")
    void getUnknownIdReturns404(){
        when().
                get("/shoppinglists/999").
        then().
                statusCode(404)
                .body("status", equalTo(404))
                .body("msg", containsString("999"));

    }

    @Test
    @DisplayName("Given a list with name, price and userId, when i post it, then i get 201 and the respnse has an id and the same name")
    void postListReturnsSameIdAndName(){
        given().
                contentType(ContentType.JSON).
                body(new ShoppingListDTO(null, "Mealprep", 12.5, user.getId())).
        when().
                post("/shoppinglists").
        then().
                statusCode(201).
                body("name", equalTo("Mealprep")).
                body("id", notNullValue());
    }

    @Test
    @DisplayName("Given a list with a blank name, when i post it, then i get 400 and msg 'name is required'")
    void postListWithMissingName(){
        given().
                contentType(ContentType.JSON).
                body(new ShoppingListDTO(null, "", 120, user.getId())).
        when().
                post("/shoppinglists").
        then().
                statusCode(400).
                body("status", equalTo(400)).
                body("msg", equalTo("name is required"));
    }

    @Test
    @DisplayName("Given a saved list, when i put a new name to /shoppinglists/{id}, then i get 200 and the list has a new name")
    void putUpdatesName(){
        ShoppingList saved = shoppingListDAO.save(ShoppingList.builder()
                .name("Casual")
                .overallPrice(34.6)
                .user(user)
                .build());
        given().
                contentType(ContentType.JSON).
                body(new ShoppingListDTO(null, "Mealprep", 34.6, user.getId())).
        when().
                put("/shoppinglists/{id}", saved.getId()).
        then().
                statusCode(200).
                body("id", equalTo(saved.getId().intValue())).//REST Assured reads numbers as int,
                // and because my getId is Long, and Hamcrest treats int ant long as different, i in turn convert the Long to int.
                body("name", equalTo("Mealprep"));
    }

    @Test
    @DisplayName("Given a saved list, when i delete it, then i get 204")
    void deleteSavedList(){
        ShoppingList saved = shoppingListDAO.save(ShoppingList.builder()
                .name("Casual")
                .overallPrice(753)
                .user(user)
                .build());
        when().
                delete("/shoppinglists/{id}", saved.getId()).
        then().
                statusCode(204);
        when().
                get("/shoppinglists/{id}", saved.getId()).
        then().
                statusCode(404);

    }

}
