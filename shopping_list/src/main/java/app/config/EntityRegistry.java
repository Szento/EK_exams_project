package app.config;

import app.entities.*;
import org.hibernate.cfg.Configuration;

final class EntityRegistry {

    private EntityRegistry() {}

    static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Authenticator.class);
        configuration.addAnnotatedClass(ItemInList.class);
        configuration.addAnnotatedClass(Offers.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(ShoppingList.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(User.class);
        // TODO: Add more entities here...
    }
}