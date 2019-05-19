package com.tinkoff.java;

import com.google.gson.Gson;
import com.tinkoff.java.model.Event;
import com.tinkoff.java.model.ParseResalt;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class Demomain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
//        Class.forName("org.h2.Driver");
//        Connection con = ConnectorDB.getConnection();
//
//        PreparedStatement pre = null;
//        pre = con.prepareStatement("SELECT*FROM EVENTS;");
//        ResultSet rs = pre.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getString("IP")+ rs.getString("FIRST_TEXT"));
//        }


        Event event = new Event();
        event.setFirstText("forget");
        event.setLang("ru");
        event.translate();
        System.out.println(event.getSecondText());





    }
}
