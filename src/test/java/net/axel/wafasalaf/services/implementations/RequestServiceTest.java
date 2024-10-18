package net.axel.wafasalaf.services.implementations;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import net.axel.wafasalaf.exception.ValidationRequestException;
import net.axel.wafasalaf.models.dtos.RequestDto;
import net.axel.wafasalaf.models.dtos.StatusDto;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.models.entities.RequestStatus;
import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.models.enums.Civility;
import net.axel.wafasalaf.repositories.interfaces.IRequestRepository;
import net.axel.wafasalaf.services.interfaces.IRequestStatusService;
import net.axel.wafasalaf.services.interfaces.IStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestServiceTest {

    @Mock
    private IRequestRepository requestRepository;

    @Mock
    private IRequestStatusService requestStatusService;

    @Mock
    private IStatusService statusService;

    private Validator validator;

    @InjectMocks
    private RequestService requestService;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        requestService = new RequestService(requestRepository, requestStatusService, validator);
    }

    @Test
    public void givenRequest_whenSaveRequest_thenReturnRequest() {
        RequestDto requestDto = new RequestDto(
                "Project A", "Software Engineer", 20000.0, 36, 500.0, "example@gmail.com",
                "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false
        );

        Request expectedRequest = new Request(
                UUID.randomUUID(),
                "Project A", "Software Engineer", 20000.0, 36, 500.0,
                "example@gmail.com", "0678123456", Civility.MR,
                "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false
        );

        when(requestRepository.save(any(Request.class))).thenReturn(expectedRequest);

        Request savedRequest = requestService.saveRequest(requestDto);

        assertNotNull(savedRequest);
        assertEquals(expectedRequest, savedRequest);
    }

    @Test
    public void givenInvalidRequest_whenSaveRequest_thenThrowValidationException() {
        RequestDto requestDto = new RequestDto(
                "",
                "Software Engineer", 20000.0, 36, 500.0, "invalid-email",
                "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false
        );

        ValidationRequestException exception = assertThrows(ValidationRequestException.class, () -> {
            requestService.saveRequest(requestDto);
        });

        Map<String, String> errors = exception.getErrors();
        assertTrue(errors.containsKey("project"));
        assertTrue(errors.containsKey("email"));
    }

    @Test
    public void givenNonExistingId_whenFindById_thenThrowException() {
        UUID nonExistingId = UUID.randomUUID();
        when(requestRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            requestService.findRequestById(nonExistingId);
        });

        assertEquals("Error getting request by ID : " + nonExistingId, exception.getMessage());
    }

    @Test
    public void givenExistingId_whenFindById_thenReturnRequest() {
        UUID existingId = UUID.randomUUID();
        Request expectedRequest = new Request(existingId, "Project A", "Software Engineer", 20000.0, 36, 500.0,
                "example@gmail.com", "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false);
        when(requestRepository.findById(existingId)).thenReturn(Optional.of(expectedRequest));

        Request foundRequest = requestService.findRequestById(existingId);

        assertNotNull(foundRequest);
        assertEquals(expectedRequest, foundRequest);
    }

    @Test
    public void givenMultipleRequests_whenFindById_thenReturnRequests() {
        UUID firstId = UUID.randomUUID();
        UUID secondId = UUID.randomUUID();

        Request firstRequest = new Request(firstId, "Project A", "Software Engineer", 20000.0, 36, 500.0,
                "example@gmail.com", "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false);

        Request secondRequest = new Request(secondId, "Project B", "Data Analyst", 25000.0, 24, 600.0,
                "example2@gmail.com", "0678123457", Civility.MISS, "Smith", "Jane", "BB12345",
                LocalDate.of(1992, 2, 2), LocalDate.of(2021, 2, 2), 30000.0, true);

        when(requestRepository.findAll()).thenReturn(List.of(firstRequest, secondRequest));

        List<Request> requests = requestService.findAllRequests(null, null);

        assertEquals(2, requests.size());
        assertTrue(requests.contains(firstRequest));
        assertTrue(requests.contains(secondRequest));
    }

    @Test
    public void givenNoFilters_whenFindAllRequests_thenReturnAllRequests() {
        List<Request> allRequests = List.of(new Request(), new Request());
        when(requestRepository.findAll()).thenReturn(allRequests);

        List<Request> result = requestService.findAllRequests(null, null);

        assertEquals(2, result.size());
    }

    @Test
    public void givenStatusFilter_whenFindAllRequests_thenReturnFilteredRequests() {
        UUID firstId = UUID.randomUUID();
        UUID secondId = UUID.randomUUID();
        Status status = new Status("pending");


        Request firstRequest = new Request(firstId, "Project A", "Software Engineer", 20000.0, 36, 500.0,
                "example@gmail.com", "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false);

        Request secondRequest = new Request(secondId, "Project B", "Data Analyst", 25000.0, 24, 600.0,
                "example2@gmail.com", "0678123457", Civility.MISS, "Smith", "Jane", "BB12345",
                LocalDate.of(1992, 2, 2), LocalDate.of(2021, 2, 2), 30000.0, true);

        RequestStatus requestStatus = new RequestStatus(firstRequest, status, LocalDateTime.now(), "this is fucking test");
        firstRequest.addRequestStatus(requestStatus);
        secondRequest.addRequestStatus(requestStatus);

        when(requestRepository.findAll()).thenReturn(List.of(firstRequest, secondRequest));

        List<Request> requests = requestService.findAllRequests("pending", null);

        assertEquals(2, requests.size());
        assertTrue(requests.contains(firstRequest));
        assertTrue(requests.contains(secondRequest));
    }

    @Test
    public void givenValidRequest_whenUpdateRequest_thenReturnUpdatedRequest() {
        UUID requestId = UUID.randomUUID();
        RequestDto requestDto = new RequestDto(
                "Project A", "Software Engineer", 20000.0, 36, 500.0, "example@gmail.com",
                "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false
        );

        Request existingRequest = new Request(requestId, "Project A", "Software Engineer", 20000.0, 36, 500.0,
                "example@gmail.com", "0678123456", Civility.MR, "Doe", "John", "AA12345",
                LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), 25000.0, false);

        when(requestRepository.update(any(Request.class))).thenReturn(existingRequest);

        Request updatedRequest = requestService.updateRequest(requestDto);

        assertNotNull(updatedRequest);
        assertEquals(existingRequest, updatedRequest);
    }

    @Test
    public void givenRequestId_whenDeleteRequest_thenRequestShouldNotExist() {
        UUID requestId = UUID.randomUUID();
        requestService.deleteRequest(requestId);

        when(requestRepository.findById(requestId)).thenReturn(Optional.empty());
        Optional<Request> deletedRequest = requestRepository.findById(requestId);

        assertFalse(deletedRequest.isPresent());
    }

}