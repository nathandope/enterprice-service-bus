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
import java.util.List;

@WebServlet("/list")
public class ListClientServlet extends HttpServlet {

    @EJB
    private ClientManagerEJBLocal clientManagerEJBLocal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        List<ClientBO> clientBOList = clientManagerEJBLocal.getAllClientBO();

        req.setAttribute("clients", clientBOList);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");

        req.getRequestDispatcher("/edit.jsp").forward(req, resp);
    }

}
