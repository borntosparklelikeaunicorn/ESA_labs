package com.example.dao;

import com.example.models.Studio;

import java.sql.SQLException;
import java.util.List;

public interface StudioDAO {
    List<Studio> getAll() throws SQLException;
}

