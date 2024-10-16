package net.axel.wafasalaf.services.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import net.axel.wafasalaf.exception.ValidationRequestException;
import net.axel.wafasalaf.models.dtos.RequestDto;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.repositories.interfaces.IRequestRepository;
import net.axel.wafasalaf.services.interfaces.IRequestService;
import net.axel.wafasalaf.services.interfaces.IRequestStatusService;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RequestService implements IRequestService {
    private final IRequestRepository requestRepository;
    private final IRequestStatusService requestStatusService;
    private final Validator validator;

    @Inject
    public RequestService(IRequestRepository requestRepository, IRequestStatusService requestStatusService, Validator validator) {
        this.requestRepository = requestRepository;
        this.requestStatusService = requestStatusService;
        this.validator = validator;
    }

    @Override
    public Request saveRequest(RequestDto dto) {
        Set<ConstraintViolation<RequestDto>> constraintViolations = validator.validate(dto);

        if (!constraintViolations.isEmpty()) {
            /*
            key => field name
            value => message
             */
            Map<String, String> errors = constraintViolations.stream().collect(Collectors.toMap(c -> c.getPropertyPath().toString(), ConstraintViolation::getMessage));
            throw new ValidationRequestException(errors);
        }

//        check if the calculation is right
        final double amount = dto.amountLoan();
        final int duration = dto.duration();
        Double monthly = checkCalcul(amount, duration);


        Request request = new Request(dto.project(), dto.work(), dto.amountLoan(), dto.duration(), monthly, dto.email(), dto.phone(), dto.civility(), dto.lastName(), dto.firstName(), dto.cin(), dto.birthDate(), dto.hiringDate(), dto.income(), dto.haveCredit());
        Request savedRequest = requestRepository.save(request);

        requestStatusService.saveRequestStatus(savedRequest);
        return savedRequest;
    }

    @Override
    public Request findRequestById(UUID id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error getting request by ID : " + id));
    }

    @Override
    public List<Request> findAllRequests(String status, LocalDateTime hiringDate) {
        List<Request> allRequests = requestRepository.findAll();

        if (status != null && !status.isEmpty()) {
            allRequests = allRequests.stream()
                    .filter(request -> request.getRequestStatuses().stream()
                            .anyMatch(reqStatus -> reqStatus.getStatus().getName().equalsIgnoreCase(status)))
                    .collect(Collectors.toList());
        }

        if(hiringDate != null) {
            allRequests = allRequests.stream()
                    .filter(request -> request.getHiringDate().isEqual(ChronoLocalDate.from(hiringDate)))
                    .toList();
        }

        return allRequests;
    }

    @Override
    public Request updateRequest(RequestDto dto) {
        Request updatedRequest = new Request();
        updatedRequest.setProject(dto.project());
        updatedRequest.setWork(dto.work());
        updatedRequest.setAmountLoan(dto.amountLoan());
        updatedRequest.setDuration(dto.duration());
        updatedRequest.setMonthly(dto.monthly());
        updatedRequest.setPhone(dto.phone());
        updatedRequest.setCivility(dto.civility());
        updatedRequest.setLast_name(dto.lastName());
        updatedRequest.setFirst_name(dto.firstName());
        updatedRequest.setBirthDate(dto.birthDate());
        updatedRequest.setHiringDate(dto.hiringDate());
        updatedRequest.setIncome(dto.income());
        updatedRequest.setHaveCredit(dto.haveCredit());
        return requestRepository.update(updatedRequest);
    }

    @Override
    public void deleteRequest(UUID id) {
        requestRepository.delete(id);
    }

    private Double checkCalcul(double amount, int duration) {
        final double interestRate = 0.012;
        final double amountTotal = amount * (1 + interestRate);
        return amountTotal / duration;
    }
}
