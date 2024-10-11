package net.axel.wafasalaf.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.axel.wafasalaf.models.dtos.StatusDto;
import net.axel.wafasalaf.repositories.implementations.StatusRepository;
import net.axel.wafasalaf.services.implementations.StatusService;
import net.axel.wafasalaf.services.interfaces.IStatusService;

import java.io.IOException;

@WebServlet(name = "admin", value = {"/admin/status", "/admin/requests"})
public class AdminServlet extends HttpServlet {
    private IStatusService statusService;
    private static final String STATUS = "/admin/status";
    private static final String REQUESTS = "/admin/requests";

    public void init() {
        statusService = new StatusService(new StatusRepository());
    }

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

    private void getRequests(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void getStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/status.jsp").forward(req, resp);
    }


}
