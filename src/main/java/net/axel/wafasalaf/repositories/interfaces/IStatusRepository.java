package net.axel.wafasalaf.repositories.interfaces;

import net.axel.wafasalaf.models.entities.Status;

import java.util.Optional;
import java.util.UUID;

public interface IStatusRepository extends IJpaRepository<Status, UUID>{
    Optional<Status> findByName(String name);
}
