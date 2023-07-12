package model;

import java.sql.ResultSet;
import java.util.Vector;

public class Student extends Mysql {

    private int id = 0;
    private String name;
    private String date;
    private String address;
    private String mobile;
    private String email;
    private String gender;

    public Student(String column, String value) {
        ResultSet student = this.get(column, value);
        try {
            if (student.next()) {
                load(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void save() {
        try {
            if (this.id == 0) {
                String query = "INSERT INTO `students`(`name`, `date_of_birth`, `address`, `email`, `mobile`, `gender_id`) VALUES('" + this.name + "', '" + this.date + "', '" + this.address + "', '" + this.email + "', '" + this.mobile + "', '" + this.gender + "')";
                insert(query);
            } else {
                Vector columns = new Vector();
                columns.add("name");
                columns.add("date_of_birth");
                columns.add("address");
                columns.add("email");
                columns.add("mobile");
                columns.add("gender_id");
                
                Vector values = new Vector();
                values.add(this.name);
                values.add(this.date);
                values.add(this.address);
                values.add(this.email);
                values.add(this.mobile);
                values.add(this.gender);
                
                int index = 0;
                for (var column : columns) {
                    String query = "UPDATE `students` SET `"+ column +"` = '"+ values.elementAt(index) +"' WHERE id = '"+ this.id +"'";
                    update(query);
                    index++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(ResultSet student) {
        try {
            this.setId(student.getInt("id"));
            this.setName(student.getString("name"));
            this.setAddress(student.getString("address"));
            this.setDate(student.getString("date_of_birth"));
            this.setMobile(student.getString("mobile"));
            this.setEmail(student.getString("email"));
            this.setGender(student.getString("gender_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet get(String column, String value) {
        try {
            String query = "SELECT * FROM `students` WHERE `" + column + "` = '" + value + "' AND `is_removed` = 0";
            return search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet like(String column, String value) {
        try {
            String query = "SELECT * FROM `students` WHERE `" + column + "` LIKE '%" + value + "%' AND `is_removed` = 0";
            return search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove() {
        try {
            String query = "UPDATE `students` SET `is_removed` = 1 WHERE `id` = '" + this.id + "'";
            update(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
