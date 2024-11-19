package com.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    public static Connection getConnection() {
        try {
            // Загрузка драйвера PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Подключение к базе данных
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/DanceStudioDB",
                    "postgres",
                    "1234"
            );
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found. Add it to your classpath.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Connection failed. Check your connection string and credentials.");
            e.printStackTrace();
            return null;
        }

    }
}
