package net.axel.wafasalaf.web;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "test", value = "/test")
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("here");
        PrintWriter out = res.getWriter();
        out.println("here");
        EntityManager wafaSalaf = Persistence.createEntityManagerFactory("WAFA_SALAF").createEntityManager();

    }
}
