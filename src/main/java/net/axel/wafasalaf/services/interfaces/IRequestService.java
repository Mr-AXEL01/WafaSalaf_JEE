package net.axel.wafasalaf.services.interfaces;

import net.axel.wafasalaf.models.dtos.RequestDto;
import net.axel.wafasalaf.models.entities.Request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IRequestService {
    Request saveRequest(RequestDto RequestDto);

    Request findRequestById(UUID id);

    List<Request> findAllRequests(String status, LocalDateTime hiringDate);

    Request updateRequest(RequestDto dto);

    void deleteRequest(UUID id);

}
