package app.dao;

import app.entities.Store;

import java.util.Optional;

public interface StoreDAO extends GenericDAO<Store, Long> {
    Optional<Store> findByName(String name);
}
