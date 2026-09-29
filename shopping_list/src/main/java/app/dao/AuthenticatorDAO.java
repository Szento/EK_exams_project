package app.dao;

import app.entities.Authenticator;

import java.util.Optional;

public interface AuthenticatorDAO extends GenericDAO<Authenticator, Long> {
    Optional<Authenticator> findByUsername(String username);
}
