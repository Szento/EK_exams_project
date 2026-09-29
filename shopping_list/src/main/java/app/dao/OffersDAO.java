package app.dao;

import app.entities.Offers;

import java.time.LocalDate;
import java.util.List;

public interface OffersDAO extends GenericDAO<Offers, Long> {
    List<Offers> findActiveByStoreId(Long storeId, LocalDate today);
}
