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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

       
        User current = (User) session.getAttribute("user");

       
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String contact = request.getParameter("contactNumber");
        String newPassword = request.getParameter("password");

       
        String error = null;

        if (fullName == null || fullName.trim().equals("")) {
            error = "Full name is required.";
        } else if (email == null || !email.contains("@")) {
            error = "Please enter a valid email address.";
        } else if (contact == null || contact.trim().length() != 10) {
            error = "Contact number must be 10 digits.";
        } else if (newPassword != null && !newPassword.equals("") && newPassword.length() < 6) {
            error = "New password must be at least 6 characters.";
        } else {
            // Is this email already used by a DIFFERENT user?
            User other = userDAO.findByEmail(email.trim());
            if (other != null && other.getUserId() != current.getUserId()) {
                error = "That email address is already used by another account.";
            }
        }

       
        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
            return;
        }

       
        User updated = new User();
        updated.setUserId(current.getUserId());
        updated.setFullName(fullName.trim());
        updated.setEmail(email.trim());
        updated.setContactNumber(contact.trim());

       
        if (newPassword != null && !newPassword.equals("")) {
            updated.setPassword(PasswordHelper.hashPassword(newPassword));
        } else {
            updated.setPassword(current.getPassword());
        }

        
        userDAO.updateUser(updated);
        session.setAttribute("user", updated);

        request.setAttribute("success", "Profile updated successfully.");
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}