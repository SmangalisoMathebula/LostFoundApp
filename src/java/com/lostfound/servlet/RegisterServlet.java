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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String contact = request.getParameter("contactNumber");

        
        String error = null;

        if (fullName == null || fullName.trim().equals("")) {
            error = "Full name is required.";
        } else if (email == null || !email.contains("@")) {
            error = "Please enter a valid email address.";
        } else if (password == null || password.length() < 6) {
            error = "Password must be at least 6 characters.";
        } else if (contact == null || contact.trim().length() != 10) {
            error = "Contact number must be 10 digits.";
        } else if (userDAO.findByEmail(email.trim()) != null) {
            error = "That email address is already registered.";
        }

       
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

       
        User user = new User();
        user.setFullName(fullName.trim());
        user.setEmail(email.trim());
        user.setPassword(PasswordHelper.hashPassword(password));  
        user.setContactNumber(contact.trim());

        boolean saved = userDAO.registerUser(user);

        if (saved) {
            response.sendRedirect("login?msg=registered");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }
}