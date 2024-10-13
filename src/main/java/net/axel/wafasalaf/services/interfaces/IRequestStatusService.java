package net.axel.wafasalaf.services.interfaces;

import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.models.entities.RequestStatus;

import java.util.UUID;

public interface IRequestStatusService {

    RequestStatus saveRequestStatus(Request request);

    RequestStatus updateRequestStatus(UUID id);
}
