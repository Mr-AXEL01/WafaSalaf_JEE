package net.axel.wafasalaf.repositories.implementations;

import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.repositories.interfaces.IRequestRepository;

import java.util.UUID;

public class RequestRepository extends JpaRepository<Request, UUID> implements IRequestRepository {
    public RequestRepository() {
        super(Request.class);
    }
}
