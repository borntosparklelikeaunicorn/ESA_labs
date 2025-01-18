<%@ page import="java.util.List" %>
<%@ page import="com.example.models.DanceClass" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Dance Classes</title>
</head>
<body>
<h1><%= "The Dance Classes page:" %></h1>
<table>
    <thead>
        <tr>
           <th>ID</th>
           <th>Style</th>
           <th>Level</th>
           <th>Schedule</th>
           <th>Studio ID</th>
        </tr>
    </thead>
    <tbody>
    <%
        List<DanceClass> classesList = (List<DanceClass>)request.getAttribute("danceClassList");
        for (DanceClass danceClass : classesList) {
    %>
    <tr>
        <td><%= danceClass.getId() %></td>
        <td><%= danceClass.getName() %></td>
        <td><%= danceClass.getLevel() %></td>
        <td><%= danceClass.getSchedule() %></td>
        <td><%= danceClass.getStudioId() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<form action="classes" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="level">Level:</label>
    <input type="text" id="level" name="level" required><br><br>

    <label for="schedule">Schedule:</label>
    <input type="text" id="schedule" name="schedule" required><br><br>

    <label for="studioId">Studio ID:</label>
    <input type="number" id="studioId" name="studioId" required><br><br>

    <input type="submit" value="Add Dance Class">
</form>
</body>
</html>

