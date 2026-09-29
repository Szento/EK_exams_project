package app.dao;

import app.entities.Authenticator;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class AuthenticatorDAOImpl extends GenericDAOImpl<Authenticator, Long> implements AuthenticatorDAO{

    public AuthenticatorDAOImpl(){
        super(Authenticator.class);
    }

    @Override
    public Optional<Authenticator> findByUsername(String username){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT a FROM Authenticator a WHERE a.username = :username", Authenticator.class)
                    .setParameter("username", username)
                    .getResultStream()
                    .findFirst();
        }
    }
}
