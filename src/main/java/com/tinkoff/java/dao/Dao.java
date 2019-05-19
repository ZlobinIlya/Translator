package com.tinkoff.java.dao;

import com.tinkoff.java.model.Event;
import com.tinkoff.java.model.Translatable;

import java.sql.SQLException;
import java.util.List;

public interface Dao {
    public List<Translatable> selectAll() throws SQLException;
    public void insert(Event event) throws SQLException;
}
