<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Studios</title>
</head>
<body>
<h1>Studios</h1>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<com.example.models.Studio> studios = (List<com.example.models.Studio>) request.getAttribute("studios");
            for (com.example.models.Studio studio : studios) {
        %>
            <tr>
                <td><%= studio.getId() %></td>
                <td><%= studio.getName() %></td>
                <td><%= studio.getAddress() %></td>
                <td><%= studio.getPhone() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

<h2>Add a New Studio</h2>
<form action="add-studio" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br><br>

    <input type="submit" value="Add Studio">
</form>
</body>
</html>
