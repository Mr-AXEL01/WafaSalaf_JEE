package net.axel.wafasalaf.services.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.axel.wafasalaf.models.dtos.StatusDto;
import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.repositories.interfaces.IStatusRepository;
import net.axel.wafasalaf.services.interfaces.IStatusService;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StatusService implements IStatusService {

    private final IStatusRepository statusRepository;

    @Inject
    public StatusService(IStatusRepository statusRepository) {
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

    @Override
    public List<Status> findAllStatuses() {
        return statusRepository.findAll();
    }
}
