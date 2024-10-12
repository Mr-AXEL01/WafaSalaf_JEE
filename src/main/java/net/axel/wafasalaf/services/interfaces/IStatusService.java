package net.axel.wafasalaf.services.interfaces;

import net.axel.wafasalaf.models.dtos.StatusDto;
import net.axel.wafasalaf.models.entities.Status;

import java.util.UUID;

public interface IStatusService {
    Status saveStatus(StatusDto dto);

    Status findStatusById(UUID id);

    Status findStatusByName(String statusName);

}
