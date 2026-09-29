package app.dao;


import app.entities.User;
import jakarta.persistence.EntityManager;


import java.util.Optional;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO{

    public UserDAOImpl(){
        super(User.class);
    }

    @Override
    public Optional<User> findByEmail(String email){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getResultStream()
                    .findFirst();
        }
    }
}
