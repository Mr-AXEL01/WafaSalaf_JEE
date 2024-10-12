package net.axel.wafasalaf.repositories.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import net.axel.wafasalaf.config.ConnectionSingleton;
import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.repositories.interfaces.IStatusRepository;

import java.util.Optional;
import java.util.UUID;

public class StatusRepository extends JpaRepository<Status, UUID> implements IStatusRepository {
    public StatusRepository() {
        super(Status.class);
    }

    public Optional<Status> findByName(String name) {
        EntityManager entityManager = ConnectionSingleton.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Status status= null;
        try {
            transaction.begin();
            status = entityManager.find(Status.class, name);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(status);
    }
}
