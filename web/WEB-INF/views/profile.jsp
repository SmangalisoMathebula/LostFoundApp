<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.lostfound.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="menu.jsp" />

<div class="box small-box">
    <h2>My Profile</h2>

    <% if (success != null) { %>
        <div class="success"><%= success %></div>
    <% } %>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="profile" method="post">
        <label>Full Name</label>
        <input type="text" name="fullName" value="<%= user.getFullName() %>" required>

        <label>Email Address</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" required>

        <label>Contact Number</label>
        <input type="text" name="contactNumber" value="<%= user.getContactNumber() %>" required>

        <label>New Password (leave blank to keep your current one)</label>
        <input type="password" name="password">

        <br><br>
        <button type="submit">Update Profile</button>
    </form>
</div>
</body>
</html>