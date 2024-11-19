package com.example.service;

import com.example.dao.DanceClassDAO;
import com.example.models.DanceClass;
import com.example.models.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanceClassService extends DbUtils implements DanceClassDAO {

    private Connection connection = getConnection();

    // Метод для добавления нового занятия
    public void addDanceClass(DanceClass danceClass) throws SQLException {
        String query = "INSERT INTO danceclass (style, level, schedule, studio_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, danceClass.getName());
            statement.setString(2, danceClass.getLevel());
            statement.setString(3, danceClass.getSchedule());
            statement.setInt(4, danceClass.getStudioId());

            statement.executeUpdate();
        }
    }

    @Override
    public List<DanceClass> getAll() throws SQLException {
        List<DanceClass> danceClassList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM danceclass");
        while (resultSet.next()) {
            DanceClass danceClass = new DanceClass();
            danceClass.setId(resultSet.getInt("id"));
            danceClass.setName(resultSet.getString("style"));
            danceClass.setLevel(resultSet.getString("level"));
            danceClass.setSchedule(resultSet.getString("schedule"));
            danceClass.setStudioId(resultSet.getInt("studio_id"));
            danceClassList.add(danceClass);
        }
        return danceClassList;
    }
}
