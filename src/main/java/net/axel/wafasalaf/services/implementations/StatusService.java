package net.axel.wafasalaf.services.implementations;

import net.axel.wafasalaf.models.dtos.StatusDto;
import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.repositories.implementations.StatusRepository;
import net.axel.wafasalaf.repositories.interfaces.IStatusRepository;
import net.axel.wafasalaf.services.interfaces.IStatusService;

import java.util.UUID;

public class StatusService implements IStatusService {

    private final IStatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status saveStatus(StatusDto dto) {
        Status status = new Status(dto.name());
        return statusRepository.save(status);
    }

    @Override
    public Status findStatusById(UUID id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error getting status by ID : " + id));
    }

    @Override
    public Status findStatusByName(String statusName) {
        return statusRepository.findByName(statusName)
                .orElseThrow(() -> new RuntimeException("Error getting status by name : " + statusName));
    }
}
