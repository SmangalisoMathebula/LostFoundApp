package com.lostfound.servlet;

import com.lostfound.dao.ItemDAO;
import com.lostfound.model.Item;
import com.lostfound.model.User;
import com.lostfound.util.LogHelper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    ItemDAO itemDAO = new ItemDAO();

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");

        if ("new".equals(action)) {
           
            request.setAttribute("item", new Item());
            request.setAttribute("formAction", "add");
            request.getRequestDispatcher("/WEB-INF/views/itemForm.jsp").forward(request, response);

        } else if ("edit".equals(action)) {
           
            int id = Integer.parseInt(request.getParameter("id"));
            Item item = itemDAO.getItemById(id);

            if (item == null) {
                response.sendRedirect("items");
                return;
            }

            request.setAttribute("item", item);
            request.setAttribute("formAction", "update");
            request.getRequestDispatcher("/WEB-INF/views/itemForm.jsp").forward(request, response);

        } else {
           
            String keyword = request.getParameter("keyword");
            String field = request.getParameter("field");
            ArrayList<Item> items;

            if (keyword != null && !keyword.trim().equals("")) {
                items = itemDAO.searchItems(field, keyword.trim());
            } else {
                items = itemDAO.getAllItems();
            }

            request.setAttribute("items", items);
            request.getRequestDispatcher("/WEB-INF/views/items.jsp").forward(request, response);
        }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");

        if (action.equals("add") || action.equals("update")) {

            
            Item item = new Item();

            String idText = request.getParameter("id");
            if (idText != null && !idText.equals("")) {
                item.setItemId(Integer.parseInt(idText));
            }

            item.setUserId(user.getUserId());
            item.setItemName(request.getParameter("itemName").trim());
            item.setItemType(request.getParameter("itemType"));
            item.setDescription(request.getParameter("description").trim());
            item.setLocation(request.getParameter("location").trim());
            item.setDateReported(request.getParameter("dateReported"));
            item.setReportType(request.getParameter("reportType"));
            item.setStatus(request.getParameter("status"));

           
            String error = null;
            if (item.getItemName().equals("")) {
                error = "Item name is required.";
            } else if (item.getLocation().equals("")) {
                error = "Location is required.";
            } else if (item.getDateReported().equals("")) {
                error = "Date reported is required.";
            }

           
            if (error != null) {
                request.setAttribute("error", error);
                request.setAttribute("item", item);
                request.setAttribute("formAction", action);
                request.getRequestDispatcher("/WEB-INF/views/itemForm.jsp").forward(request, response);
                return;
            }

            if (action.equals("add")) {
                itemDAO.addItem(item);
                LogHelper.writeLog(user.getEmail(), "Added item", item.getItemName());
                response.sendRedirect("items?msg=added");
            } else {
                itemDAO.updateItem(item);
                LogHelper.writeLog(user.getEmail(), "Updated item", item.getItemName());
                response.sendRedirect("items?msg=updated");
            }

        } else if (action.equals("claim")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Item item = itemDAO.getItemById(id);     // we need the name for the log

            if (item != null) {
                itemDAO.markAsClaimed(id);
                LogHelper.writeLog(user.getEmail(), "Marked item as claimed", item.getItemName());
            }
            response.sendRedirect("items?msg=claimed");

        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Item item = itemDAO.getItemById(id);     // get the name BEFORE deleting

            if (item != null) {
                itemDAO.deleteItem(id);
                LogHelper.writeLog(user.getEmail(), "Deleted item", item.getItemName());
            }
            response.sendRedirect("items?msg=deleted");
        }
    }
}