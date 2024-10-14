package net.axel.wafasalaf.services.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.axel.wafasalaf.models.dtos.RequestStatusDto;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.models.entities.RequestStatus;
import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.repositories.interfaces.IRequestStatusRepository;
import net.axel.wafasalaf.services.interfaces.IRequestService;
import net.axel.wafasalaf.services.interfaces.IRequestStatusService;
import net.axel.wafasalaf.services.interfaces.IStatusService;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class RequestStatusService implements IRequestStatusService {

    private final IRequestStatusRepository requestStatusRepository;
    private final IStatusService statusService;
    private final IRequestService requestService;

    @Inject
    public RequestStatusService(IRequestStatusRepository requestStatusRepository, IStatusService statusService, IRequestService requestService) {
        this.requestStatusRepository = requestStatusRepository;
        this.statusService = statusService;
        this.requestService = requestService;
    }

    @Override
    public RequestStatus saveRequestStatus(Request request) {
        Status pending = statusService.findStatusByName("pending");
        String description = "the request is on pending until the admin approve it or reject it .";
        LocalDateTime changedDate = LocalDateTime.now();

        RequestStatus requestStatus = new RequestStatus(request, pending, changedDate, description);
        return requestStatusRepository.save(requestStatus);
    }

    @Override
    public RequestStatus updateRequestStatus(RequestStatusDto dto) {
        Request request = requestService.findRequestById(dto.requestId());
        Status status = statusService.findStatusById(dto.statusId());
        LocalDateTime changedDate = LocalDateTime.now();
        RequestStatus updatedRequestStatus = new RequestStatus(request, status, changedDate, dto.description());
        return requestStatusRepository.save(updatedRequestStatus);
    }
}
