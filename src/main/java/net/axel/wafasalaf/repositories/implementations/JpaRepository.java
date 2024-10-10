package net.axel.wafasalaf.repositories.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import net.axel.wafasalaf.config.ConnectionSingleton;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.repositories.interfaces.IJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        T entity = null;
        try {
            transaction.begin();
            entity = entityManager.find(entityType, id);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        try {
            transaction.begin();
            entities = entityManager.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", entityType).getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return entities;
    }

    @Override
    public T update(T entity) {
        T updatedEntity = null;
        try {
            transaction.begin();
            updatedEntity = entityManager.merge(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return updatedEntity;
    }

    @Override
    public void delete(ID id) {
        try {
            transaction.begin();
            T entity = entityManager.find(entityType, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
