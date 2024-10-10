package net.axel.wafasalaf.repositories.interfaces;

import net.axel.wafasalaf.models.entities.RequestStatus;

import java.util.UUID;

public interface IRequestStatusRepository extends IJpaRepository<RequestStatus, UUID> {
}
