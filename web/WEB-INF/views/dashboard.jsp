<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.lostfound.model.User"%>
<%
    
    User user = (User) session.getAttribute("user");

   
    int total = (Integer) request.getAttribute("total");
    int unclaimed = (Integer) request.getAttribute("unclaimed");
    int claimed = (Integer) request.getAttribute("claimed");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="menu.jsp" />

<div class="box">
    <h2>Welcome, <%= user.getFullName() %>!</h2>

    <div class="card">
        <p>Total Reported Items</p>
        <h1><%= total %></h1>
    </div>

    <div class="card">
        <p>Unclaimed Items</p>
        <h1><%= unclaimed %></h1>
    </div>

    <div class="card">
        <p>Claimed Items</p>
        <h1><%= claimed %></h1>
    </div>
</div>
</body>
</html>