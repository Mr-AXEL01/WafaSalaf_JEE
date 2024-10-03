package net.axel.wafasalaf.services.implementations;

import net.axel.wafasalaf.models.dtos.RequestDto;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.repositories.implementations.RequestRepository;
import net.axel.wafasalaf.repositories.interfaces.IRequestRepository;
import net.axel.wafasalaf.services.interfaces.IRequestService;

import java.util.List;
import java.util.UUID;

public class RequestService implements IRequestService {
    private final IRequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request saveRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setProject(requestDto.project());
        request.setWork(requestDto.work());
        request.setAmountLoan(requestDto.amountLoan());
        request.setDuration(requestDto.duration());
        request.setMonthly(requestDto.monthly());
        request.setPhone(requestDto.phone());
        request.setCivility(requestDto.civility());
        request.setLast_name(requestDto.lastName());
        request.setFirst_name(requestDto.firstName());
        request.setBirthday(requestDto.birthday());
        request.setHiringDay(requestDto.hiringDay());
        request.setIncome(requestDto.income());
        request.setHaveCredit(requestDto.haveCredit());

        return requestRepository.save(request);
    }

    @Override
    public Request findRequestById(UUID id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error getting request by ID : " + id));
    }

    @Override
    public List<Request> findAllRequests() {
        return List.of();
    }

    @Override
    public Request updateRequest(String oldName, RequestDto updatedRequest) {
        return null;
    }

    @Override
    public void deleteRequest(String name) {

    }
}
