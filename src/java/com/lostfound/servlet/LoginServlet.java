package com.lostfound.servlet;

import com.lostfound.dao.UserDAO;
import com.lostfound.model.User;
import com.lostfound.util.PasswordHelper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

       
        if (email == null || email.trim().equals("") || password == null || password.equals("")) {
            request.setAttribute("error", "Please enter your email and password.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

       
        User user = userDAO.findByEmail(email.trim());
        String hashedPassword = PasswordHelper.hashPassword(password);

       
        if (user != null && user.getPassword().equals(hashedPassword)) {

           
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}