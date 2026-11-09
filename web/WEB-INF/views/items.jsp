<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.lostfound.model.Item"%>
<%
    
    ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items");

   
    String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Items</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="menu.jsp" />

<div class="box">
    <h2>Lost and Found Items</h2>

    <% if (msg != null && msg.equals("added")) { %>
        <div class="success">Item reported successfully.</div>
    <% } %>
    <% if (msg != null && msg.equals("updated")) { %>
        <div class="success">Item updated successfully.</div>
    <% } %>
    <% if (msg != null && msg.equals("claimed")) { %>
        <div class="success">Item marked as claimed.</div>
    <% } %>
    <% if (msg != null && msg.equals("deleted")) { %>
        <div class="success">Item deleted.</div>
    <% } %>

    <!-- Search form -->
    <form action="items" method="get">
        <select name="field" style="width:150px">
            <option value="name">Item Name</option>
            <option value="location">Location</option>
        </select>
        <input type="text" name="keyword" placeholder="Search..." style="width:200px">
        <button type="submit">Search</button>
        <a class="btn grey" href="items">Clear</a>
    </form>
    <br>

    <div class="table-area">
    <table>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Description</th>
            <th>Location</th>
            <th>Date Reported</th>
            <th>Report Type</th>
            <th>Status</th>
            <th>Claim Deadline</th>
            <th>Actions</th>
        </tr>

        <% for (int n = 0; n < items.size(); n++) {
               Item i = items.get(n); %>
        <tr>
            <td><%= i.getItemName() %></td>
            <td><%= i.getItemType() %></td>
            <td><%= i.getDescription() %></td>
            <td><%= i.getLocation() %></td>
            <td><%= i.getDateReported() %></td>
            <td><%= i.getReportType() %></td>
            <td><%= i.getStatus() %></td>
            <td><%= i.getClaimDeadline() %></td>
            <td>
                <a class="btn" href="items?action=edit&id=<%= i.getItemId() %>">Edit</a>

                <% if (i.getStatus().equals("Unclaimed")) { %>
                <form class="inline" action="items" method="post">
                    <input type="hidden" name="action" value="claim">
                    <input type="hidden" name="id" value="<%= i.getItemId() %>">
                    <button class="green" type="submit">Claim</button>
                </form>
                <% } %>

                <form class="inline" action="items" method="post"
                      onsubmit="return confirm('Delete this item?');">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= i.getItemId() %>">
                    <button class="red" type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>

        <% if (items.size() == 0) { %>
        <tr><td colspan="9">No items found.</td></tr>
        <% } %>
    </table>
    </div>
</div>
</body>
</html>