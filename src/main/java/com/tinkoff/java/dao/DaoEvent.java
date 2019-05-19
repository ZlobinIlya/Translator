package com.tinkoff.java.dao;

import com.tinkoff.java.dao.connection.ConnectorDB;
import com.tinkoff.java.model.Event;
import com.tinkoff.java.model.Translatable;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEvent implements Dao {

    Connection con = ConnectorDB.getConnection();

    public DaoEvent() throws ClassNotFoundException, SQLException, FileNotFoundException {
    }

    @Override
    public List<Translatable> selectAll() throws SQLException {
        List<Translatable> events = new ArrayList<>();

        String selectSQL = "SELECT*FROM EVENTS;";
        PreparedStatement prep = null;

        try {
            prep = con.prepareStatement(selectSQL);
            ResultSet rs = null;
            try {
                rs = prep.executeQuery();
                while (rs.next()) {
                    events.add(new Event(rs.getString("IP"), rs.getString("FIRST_TEXT")));
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prep != null) {
                prep.close();
            } else {
                System.err.println("Statement не создан");
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);

                }
            }
        }

        return events;
    }


    @Override
    public void insert(Event event) throws SQLException {
        String insertSQL = "INSERT INTO EVENTS (IP,FIRST_TEXT,DATE) VALUES(?,?,?);";
        PreparedStatement prep = null;
        try {
            prep = con.prepareStatement(insertSQL);

            prep.setString(1, event.getIp());
            prep.setString(2, event.getFirstText());
            prep.setString(3, String.valueOf(event.getDate()));
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prep != null) {
                prep.close();
            } else {
                System.err.println("Statement не создан");
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Сonnection close error: " + e);

                }
            }
        }
    }
}