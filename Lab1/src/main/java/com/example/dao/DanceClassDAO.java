package com.example.dao;

import com.example.models.DanceClass;

import java.sql.SQLException;
import java.util.List;

public interface DanceClassDAO {
    List<DanceClass> getAll() throws SQLException;
}
