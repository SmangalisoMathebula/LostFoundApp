package com.lostfound.servlet;

import com.lostfound.dao.ItemDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    ItemDAO itemDAO = new ItemDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        
        int total = itemDAO.countAll();
        int unclaimed = itemDAO.countByStatus("Unclaimed");
        int claimed = itemDAO.countByStatus("Claimed");

        request.setAttribute("total", total);
        request.setAttribute("unclaimed", unclaimed);
        request.setAttribute("claimed", claimed);

        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }
}