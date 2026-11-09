<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    String error = (String) request.getAttribute("error");
    String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="box small-box">
    <h2>Lost and Found - Login</h2>

    <% if (msg != null && msg.equals("registered")) { %>
        <div class="success">Registration successful. Please log in.</div>
    <% } %>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="login" method="post">
        <label>Email Address</label>
        <input type="email" name="email" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <br><br>
        <button type="submit">Login</button>
    </form>

    <p>No account? <a href="register">Register here</a></p>
</div>
</body>
</html>