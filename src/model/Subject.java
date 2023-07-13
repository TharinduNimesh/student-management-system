package model;

import java.sql.*;

public class Subject extends Mysql {

    private int id = 0;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save() {
        try {
            String query = "INSERT INTO `subjects`(`name`) VALUES('" + this.name + "')";
            insert(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet all() {
        try {
            String query = "SELECT * FROM subjects";
            return search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void remove(String id) {
        try {
            String query = "DELETE FROM subjects WHERE id = '"+ id +"'";
            delete(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
