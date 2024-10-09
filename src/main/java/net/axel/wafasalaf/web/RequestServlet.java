package net.axel.wafasalaf.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.axel.wafasalaf.exception.ValidationRequestException;
import net.axel.wafasalaf.models.dtos.RequestDto;
import net.axel.wafasalaf.models.entities.Request;
import net.axel.wafasalaf.models.enums.Civility;
import net.axel.wafasalaf.repositories.implementations.RequestRepository;
import net.axel.wafasalaf.services.implementations.RequestService;
import net.axel.wafasalaf.services.interfaces.IRequestService;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@WebServlet(name = "addRequest", value = "/request")
public class RequestServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RequestServlet.class.getName());

    //@Inject
    private IRequestService requestService;

    @Override
    public void init() {
        requestService = new RequestService(new RequestRepository());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        check the request parameters.
//        logRequestDetails(req);

        try {
            String project = req.getParameter("project");
            String work = req.getParameter("who");
            double amountLoan = Double.parseDouble(req.getParameter("amount"));
            int duration = Integer.parseInt(req.getParameter("duration"));
            double monthly = Double.parseDouble(req.getParameter("monthly"));
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Civility civility = Civility.valueOf(req.getParameter("civility"));
            String lastName = req.getParameter("last-name");
            String firstName = req.getParameter("first-name");
            String cin = req.getParameter("cin");
            LocalDate birthDate = LocalDate.parse(req.getParameter("birthday"));
            LocalDate hiringDate = LocalDate.parse(req.getParameter("hiring-day"));
            double income = Double.parseDouble(req.getParameter("income"));
            boolean haveCredit = Boolean.parseBoolean(req.getParameter("have-credit"));

            RequestDto requestDto = new RequestDto(
                    project,
                    work,
                    amountLoan,
                    duration,
                    monthly,
                    email,
                    phone,
                    civility,
                    lastName,
                    firstName,
                    cin,
                    birthDate,
                    hiringDate,
                    income,
                    haveCredit
            );

            Request request = requestService.saveRequest(requestDto);
//            res.setStatus(HttpServletResponse.SC_CREATED);
//            res.getWriter().write("Request successfully created" + request);

            res.sendRedirect(req.getContextPath() + "/?status=success");

        } catch (ValidationRequestException e) {
            logger.error("Validation error: " + e.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Internal error: " + e.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("Internal server error: " + e.getMessage());
        }
    }

    private void logRequestDetails(HttpServletRequest request) {
        // Log the request method and URI
        logger.info("Request Method: " + request.getMethod());

        // Log all request parameters (for GET or POST)
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            logger.info("Parameter: " + entry.getKey() + " = " + String.join(",", entry.getValue()));
        }

    }
}
