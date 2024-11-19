package com.example.service;

import com.example.dao.StudioDAO;
import com.example.models.Studio;
import com.example.models.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanceStudioService extends DbUtils implements StudioDAO {

    private Connection connection = getConnection();

    @Override
    public List<Studio> getAll() throws SQLException {
        List<Studio> studios = new ArrayList<>();
        String query = "SELECT * FROM dancestudio";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Studio studio = new Studio();
                studio.setId(resultSet.getInt("id"));
                studio.setName(resultSet.getString("name"));
                studio.setAddress(resultSet.getString("address"));
                studio.setPhone(resultSet.getString("phone"));
                studios.add(studio);
            }
        }
        return studios;
    }

    public void addStudio(Studio studio) throws SQLException {
        String query = "INSERT INTO dancestudio (name, address, phone) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studio.getName());
            statement.setString(2, studio.getAddress());
            statement.setString(3, studio.getPhone());
            statement.executeUpdate();
        }
    }
}
