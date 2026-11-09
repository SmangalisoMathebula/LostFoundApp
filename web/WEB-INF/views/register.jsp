<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="box small-box">
    <h2>Create an Account</h2>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="register" method="post">
        <label>Full Name</label>
        <input type="text" name="fullName" required>

        <label>Email Address</label>
        <input type="email" name="email" required>

        <label>Password (at least 6 characters)</label>
        <input type="password" name="password" required>

        <label>Contact Number (10 digits)</label>
        <input type="text" name="contactNumber" required>

        <br><br>
        <button type="submit">Register</button>
    </form>

    <p>Already registered? <a href="login">Login</a></p>
</div>
</body>
</html>