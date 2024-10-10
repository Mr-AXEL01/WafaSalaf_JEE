package net.axel.wafasalaf.repositories.implementations;

import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.repositories.interfaces.IStatusRepository;

import java.util.UUID;

public class StatusRepository extends JpaRepository<Status, UUID> implements IStatusRepository {
    public StatusRepository() {
        super(Status.class);
    }
}
