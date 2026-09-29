package app.dao;

import app.entities.User;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User, Long> {
    Optional<User> findByEmail(String email);
}

