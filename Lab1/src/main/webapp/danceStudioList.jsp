<%@ page import="java.util.List" %>
<%@ page import="com.example.models.DanceStudio" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Dance Studios</title>
</head>
<body>
<h1><%= "The Dance Studios page:" %>
    <table>
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
            List<DanceStudio> danceStudioList = (List<DanceStudio>)request.getAttribute("danceStudioList");
            for (DanceStudio studio : danceStudioList) {
        %>
        <tr>
             <td><%= studio.getId() %></td>
             <td><%= studio.getName() %></td>
             <td><%= studio.getAddress() %></td>
             <td><%= studio.getPhone() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <form action="studios" method="post">
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