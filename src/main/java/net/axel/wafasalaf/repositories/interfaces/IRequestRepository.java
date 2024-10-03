package net.axel.wafasalaf.repositories.interfaces;

import net.axel.wafasalaf.models.entities.Request;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRequestRepository {
    Request save(Request request);

    Optional<Request> findById(UUID id);

    List<Request> findAll();

    Request update(Request request);

    void delete(UUID id);
}
