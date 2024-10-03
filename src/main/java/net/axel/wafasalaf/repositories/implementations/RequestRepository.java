package net.axel.wafasalaf.repositories.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import net.axel.wafasalaf.config.ConnectionSingleton;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.repositories.interfaces.IRequestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestRepository implements IRequestRepository {

    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public RequestRepository() {
        this.entityManager = ConnectionSingleton.entityManagerFactory().createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public Request save(Request request) {
        try {
            transaction.begin();
            entityManager.persist(request);
            transaction.commit();
        } catch(RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return request;
    }

    @Override
    public Optional<Request> findById(UUID id) {
        try {
            transaction.begin();
            entityManager.find()
        }
        return Optional.empty();
    }

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        return List.of();
    }

    @Override
    public Request update(Request request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
