package com0proje.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {

    private static String url = null;
    private static String user = null;
    private static String password = null;

    static {

        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(inputStream);

            url = properties.getProperty("db_url");
            user = properties.getProperty("db_user");
            password = properties.getProperty("db_password");

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static Connection getConnection(){

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Mysql baglandık .");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return connection;
    }

    public static void closeConnection(Connection connection ){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }



}
