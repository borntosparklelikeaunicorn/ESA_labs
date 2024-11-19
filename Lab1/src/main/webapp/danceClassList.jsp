<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Dance Classes</title>
</head>
<body>
<h1>Dance Classes</h1>

<!-- Добавим таблицу для отображения данных -->
<table border="1">
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
            List<com.example.models.DanceClass> danceClasses = (List<com.example.models.DanceClass>) request.getAttribute("danceClasses");
            for (com.example.models.DanceClass danceClass : danceClasses) {
        %>
            <tr>
                <td><%= danceClass.getId() %></td>
                <td><%= danceClass.getName() %></td>
                <td><%= danceClass.getLevel() %></td>
                <td><%= danceClass.getSchedule() %></td>
                <td><%= danceClass.getStudioId() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

<!-- Форма для добавления нового занятия -->
<h2>Add a New Dance Class</h2>
<form action="add-dance-class" method="post">
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
