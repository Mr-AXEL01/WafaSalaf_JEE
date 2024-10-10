package net.axel.wafasalaf.repositories.implementations;

import net.axel.wafasalaf.models.entities.RequestStatus;
import net.axel.wafasalaf.repositories.interfaces.IRequestStatusRepository;

import java.util.UUID;

public class RequestStatusRepository extends JpaRepository<RequestStatus, UUID> implements IRequestStatusRepository {
    public RequestStatusRepository() {
        super(RequestStatus.class);
    }
}
