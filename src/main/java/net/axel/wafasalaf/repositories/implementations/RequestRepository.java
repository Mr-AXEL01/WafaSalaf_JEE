package net.axel.wafasalaf.repositories.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
        Request request = null;
        try {
            transaction.begin();
            request = entityManager.find(Request.class, id);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(request);
    }

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        try {
            transaction.begin();
            requests = entityManager.createQuery("SELECT r FROM Request r", Request.class).getResultList();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return requests;
    }

    @Override
    public Request update(Request request) {
        Request updatedRequest = null;
        try {
            transaction.begin();
            updatedRequest = entityManager.merge(request);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return updatedRequest;
    }

    @Override
    public void delete(UUID id) {
        try {
            transaction.begin();
            Request request = entityManager.find(Request.class, id);
            if (request != null) {
            entityManager.remove(request);
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
