package com.tinkoff.java.dao.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorDB {
    public static final String PATH_TO_PROPERTIES_postgres = "C:\\JAVA_WORK\\Tinkoff\\Translator\\src\\main\\java\\com\\tinkoff\\java\\dao\\connection\\database.properties";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES_postgres);
            prop.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл src/main/com/epam/zlobin/connection/database.properties не обнаружено");
        }
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("password");
        Class.forName(prop.getProperty("driver"));
        return DriverManager.getConnection(url, user, pass);
    }
}
