<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.lostfound.model.Item"%>
<%
    
    Item item = (Item) request.getAttribute("item");

   
    String formAction = (String) request.getAttribute("formAction");

    String error = (String) request.getAttribute("error");

    
    String type = item.getItemType();
    String reportType = item.getReportType();
    String status = item.getStatus();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Item Form</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="menu.jsp" />

<div class="box small-box">

    <% if (formAction.equals("add")) { %>
        <h2>Report an Item</h2>
    <% } else { %>
        <h2>Update Item</h2>
    <% } %>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="items" method="post">
        <input type="hidden" name="action" value="<%= formAction %>">
        <input type="hidden" name="id" value="<%= item.getItemId() %>">

        <label>Item Name</label>
        <input type="text" name="itemName" value="<%= item.getItemName() %>" required>

        <label>Item Type</label>
        <select name="itemType">
            <option value="Electronic" <% if (type.equals("Electronic")) { %>selected<% } %>>Electronic</option>
            <option value="Personal Item" <% if (type.equals("Personal Item")) { %>selected<% } %>>Personal Item</option>
            <option value="Clothing" <% if (type.equals("Clothing")) { %>selected<% } %>>Clothing</option>
            <option value="Other" <% if (type.equals("Other")) { %>selected<% } %>>Other</option>
        </select>

        <label>Description</label>
        <textarea name="description" rows="3"><%= item.getDescription() %></textarea>

        <label>Location</label>
        <input type="text" name="location" value="<%= item.getLocation() %>" required>

        <label>Date Reported</label>
        <input type="date" name="dateReported" value="<%= item.getDateReported() %>" required>

        <label>Report Type</label>
        <select name="reportType">
            <option value="Lost" <% if (reportType.equals("Lost")) { %>selected<% } %>>Lost</option>
            <option value="Found" <% if (reportType.equals("Found")) { %>selected<% } %>>Found</option>
        </select>

        <label>Status</label>
        <% if (formAction.equals("add")) { %>
            <!-- A new item is always Unclaimed -->
            <input type="text" value="Unclaimed" disabled>
            <input type="hidden" name="status" value="Unclaimed">
        <% } else { %>
            <select name="status">
                <option value="Unclaimed" <% if (status.equals("Unclaimed")) { %>selected<% } %>>Unclaimed</option>
                <option value="Claimed" <% if (status.equals("Claimed")) { %>selected<% } %>>Claimed</option>
            </select>
        <% } %>

        <br><br>
        <button type="submit">Save</button>
        <a class="btn grey" href="items">Cancel</a>
    </form>
</div>
</body>
</html>