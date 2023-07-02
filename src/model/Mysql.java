package model;

import java.sql.*;

public class Mysql {

    private static Connection connection;

    public static void connect(String host, String username, String password, String database) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "", username, password);
    }

    public static boolean checkConnection() {
        if (connection == null) {
            return false;
        }
        return true;
    }

    public static void closeConnection() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void insert(String query) throws Exception {
        run(query);
    }

    protected static void update(String query) throws Exception {
        run(query);
    }

    protected static void delete(String query) throws Exception {
        run(query);
    }

    public static ResultSet search(String query) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private static void run(String query) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
