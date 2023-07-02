package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User extends Mysql {

    private int id = 0;
    private String name;
    private String nic;
    private String mobile;
    private String gender;
    private String address;
    private String email;
    private String password;
    private String role;

    public User(int id) throws Exception {
        ResultSet result = this.get(id);
        if(result.next()) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean save() {
        if (this.id == 0) {
            String query = "INSERT INTO users(`name`, `nic`, `mobile`, `address`, `email`, `password`, `gender_id`, `role_id`)"
                    + "VALUE('" + this.name + "', "
                    + "'" + this.nic + "', "
                    + "'" + this.mobile + "', "
                    + "'" + this.address + "', "
                    + "'" + this.email + "', "
                    + "'" + this.password + "', "
                    + "'" + this.gender + "', "
                    + "'2')";
            try {
                insert(query);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
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

    private ResultSet get(int id) {
        String query = "SELECT u.*, g.type AS gender, r.type AS role FROM users u WHERE id = '" + id + "' "
                + "INNER JOIN genders g ON g.id = u.gender_id "
                + "INNER JOIN roles r ON r.id = u.role_id";
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
            this.address = result.getString("address");
            this.email = result.getString("email");
            this.password = result.getString("password");
            this.role = result.getString("role");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
