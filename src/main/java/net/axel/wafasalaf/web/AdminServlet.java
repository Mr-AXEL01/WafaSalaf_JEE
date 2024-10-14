package net.axel.wafasalaf.web;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.axel.wafasalaf.models.dtos.RequestStatusDto;
import net.axel.wafasalaf.models.dtos.StatusDto;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.models.entities.Status;
import net.axel.wafasalaf.services.interfaces.IRequestService;
import net.axel.wafasalaf.services.interfaces.IRequestStatusService;
import net.axel.wafasalaf.services.interfaces.IStatusService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "admin", value = {"/admin/status", "/admin/requests", "/admin/request-details"})
public class AdminServlet extends HttpServlet {
    @Inject
    private IStatusService statusService;
    @Inject
    private IRequestService requestService;
    @Inject
    private IRequestStatusService requestStatusService;
    private static final String STATUS = "/admin/status";
    private static final String REQUESTS = "/admin/requests";
    private static final String REQUEST_DETAILS = "/admin/request-details";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getServletPath();
        switch (action) {
            case STATUS:
                this.getStatus(req, resp);
                break;
            case REQUESTS:
                this.getRequests(req, resp);
                break;
            case REQUEST_DETAILS:
                this.getRequestDetails(req, resp);
                break;
            default:
                System.out.println("action not valid!"); //todo exception
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String action = req.getServletPath();
        switch (action) {
            case STATUS:
                this.addStatus(req, resp);
                break;
            case REQUEST_DETAILS:
                this.assignStatus(req, resp);
                break;
            default:
                System.out.println("invalid action!"); //todo exception

        }
    }

    private void addStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("statusName");

        StatusDto dto = new StatusDto(
                name
        );

        statusService.saveStatus(dto);
        resp.sendRedirect(req.getContextPath() + "/admin/status");
    }

    private void getRequests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        String hiringDateStr = req.getParameter("hiring_date");

        LocalDateTime hiringDate = null;
        if (hiringDateStr != null && !hiringDateStr.isEmpty()) {
            try {
                hiringDate = LocalDateTime.parse(hiringDateStr);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        }

        List<Request> requests = requestService.findAllRequests(status, hiringDate);

        req.setAttribute("requests", requests);

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    private void getStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/status.jsp").forward(req, resp);
    }

    private void getRequestDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestId = req.getParameter("id");

        Request request = requestService.findRequestById(UUID.fromString(requestId));

        req.setAttribute("request", request);
        req.setAttribute("requestStatuses", request.getRequestStatuses());

        List<Status> allStatuses = statusService.findAllStatuses();
        req.setAttribute("allStatuses", allStatuses);

        req.getRequestDispatcher("/request-details.jsp").forward(req, resp);
    }

    private void assignStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestId = req.getParameter("id");
        String newStatus = req.getParameter("status");
        String description = req.getParameter("description");

        Status status = statusService.findStatusByName(newStatus);

        RequestStatusDto dto = new RequestStatusDto(UUID.fromString(requestId), status.getId(), description);

        requestStatusService.updateRequestStatus(dto);

        resp.sendRedirect(req.getContextPath() + "/admin/request-details?id=" + requestId);
    }


}
