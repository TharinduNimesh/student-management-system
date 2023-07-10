package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class User extends Mysql {

    private int id = 0;
    private String name;
    private String nic;
    private String mobile;
    private String gender;
    private String email;
    private String password;
    private String role;
    private String created_at;
    private String secure_key;

    public User(int id) throws Exception {
        ResultSet result = this.get("id", String.valueOf(id));
        if (result.next()) {
            this.setUser(result);
        }
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setSecure_key(String secure_key) {
        this.secure_key = secure_key;
    }

    public boolean save() throws Exception {
        ResultSet result = this.get("email", this.email);
        if (!result.next()) {
            String validate = "SELECT * FROM secret_key WHERE `key` = '" + this.secure_key + "'";
            ResultSet keys = search(validate);

            if (keys.next()) {
                String query = "INSERT INTO users(`name`, `nic`, `mobile`, `email`, `password`, `gender_id`, `created_at`, `role_id`)"
                        + "VALUE('" + this.name + "', "
                        + "'" + this.nic + "', "
                        + "'" + this.mobile + "', "
                        + "'" + this.email + "', "
                        + "'" + this.password + "', "
                        + "'" + this.gender + "', "
                        + "'" + Common.getCurrentDate() + "', "
                        + "'2')";
                insert(query);
                return true;
            }
        }
        return false;
    }

    public boolean attempt(String email, String password) {
        String query = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
        try {
            ResultSet result = search(query);
            if (result.next()) {
                return result.getString("email").equals(email)
                        && result.getString("password").equals(password);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private ResultSet get(String property, String value) {
        String query = "SELECT u.*, g.type AS gender, r.type AS role FROM users u "
                + "INNER JOIN genders g ON g.id = u.gender_id "
                + "INNER JOIN roles r ON r.id = u.role_id "
                + "WHERE " + property + " = '" + value + "' ";
        try {
            return search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isAdmin() {
        return this.role.equals("Admin");
    }

    private void setUser(ResultSet result) {
        try {
            this.name = result.getString("name");
            this.nic = result.getString("nic");
            this.mobile = result.getString("mobile");
            this.gender = result.getString("gender");
            this.email = result.getString("email");
            this.password = result.getString("password");
            this.role = result.getString("role");
            this.created_at = result.getString("created_at");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
