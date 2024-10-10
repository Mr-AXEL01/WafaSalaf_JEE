package net.axel.wafasalaf.repositories.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import net.axel.wafasalaf.config.ConnectionSingleton;
import net.axel.wafasalaf.repositories.interfaces.IJpaRepository;

import java.util.List;
import java.util.Optional;

public class JpaRepository<T, ID> implements IJpaRepository<T, ID> {

    private final EntityManager entityManager;
    private final EntityTransaction transaction;
    private final Class<T> entityType;

    public JpaRepository(Class<T> entityType) {
        this.entityManager = ConnectionSingleton.entityManagerFactory().createEntityManager();
        this.transaction = entityManager.getTransaction();
        this.entityType = entityType;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public void delete(Object o) {

    }
}
