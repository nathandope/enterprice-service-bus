package dope.nathan.application.client_servlet;

import dope.nathan.application.asbo.ClientBO;
import dope.nathan.application.ejb.impl.ClientManagerEJBLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/edit")
public class EditClientServlet extends HttpServlet {

    @EJB
    private ClientManagerEJBLocal clientManagerEJBLocal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        int id = Integer.valueOf(req.getParameter("id"));
        ClientBO clientBO = clientManagerEJBLocal.getClientBO(id);

        req.setAttribute("client", clientBO);
        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        int id = Integer.valueOf(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birthDate"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        ClientBO clientBO = clientManagerEJBLocal.getClientBO(id);
        clientBO.setLogin(login);
        clientBO.setPassword(password);
        clientBO.setBirthDate(birthDate);
        clientBO.setFirstName(firstName);
        clientBO.setLastName(lastName);

        clientManagerEJBLocal.editClientBO(clientBO);

        resp.sendRedirect("list");
    }
}