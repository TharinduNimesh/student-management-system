package model;

import java.sql.*;
import java.util.Vector;
import static model.Mysql.update;

public class Teacher extends Mysql {

    public Teacher() {

    }

    public Teacher(String column, String value) {
        ResultSet teacher = this.get(column, value);
        try {
            if (teacher.next()) {
                load(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int id = 0;
    private String name;
    private String address;
    private String mobile;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void save() {
        try {
            if (this.id == 0) {
                String query = "INSERT INTO `teachers`(`name`, `address`, `mobile`) VALUES('" + this.name + "', '" + this.address + "', '" + this.mobile + "')";
                insert(query);
            } else {
                Vector columns = new Vector();
                columns.add("name");
                columns.add("address");
                columns.add("mobile");

                Vector values = new Vector();
                values.add(this.name);
                values.add(this.address);
                values.add(this.mobile);

                int index = 0;
                for (var column : columns) {
                    String query = "UPDATE `students` SET `" + column + "` = '" + values.get(index) + "' WHERE id = '" + this.id + "'";
                    update(query);
                    index++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        try {
            String query = "DELETE FROM `teachers` WHERE `id` = " + this.id + "";
            delete(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet get(String column, String value) {
        try {
            String query = "SELECT * FROM `teachers` WHERE `" + column + "` = '" + value + "'";
            return search(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getSubjects() throws Exception {
        String query = "SELECT * FROM `teacher_has_subjects` WHERE `teacher_id` = '" + this.id + "'";
        return search(query);
    }

    public static ResultSet all() throws Exception {
        String query = "SELECT * FROM `teachers`";
        return search(query);
    }
    
    public void addSubjects(Vector subjects) {
        try {
            Vector<Integer> already = new Vector<>();
            ResultSet exists = this.getSubjects();

            while (exists.next()) {
                already.add(exists.getInt("subject_id"));
            }
            for (var subject : subjects) {
                if (!already.contains(subject)) {
                    String query = "INSERT INTO `teacher_has_subjects`(`subject_id`, `teacher_id`) VALUES('" + subject + "', '" + this.id + "')";
                    insert(query);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void removeSubject(int SubjectID) {
        try {
            String query = "DELETE FROM `teacher_has_subjects` WHERE teacher_id = "+ this.id +" AND subject_id = "+ SubjectID +"";
            delete(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load(ResultSet teacher) {
        try {
            this.id = teacher.getInt("id");
            this.name = teacher.getString("name");
            this.address = teacher.getString("address");
            this.mobile = teacher.getString("mobile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet like(String column, String value) throws Exception {
        String query = "SELECT * FROM `teachers` WHERE `" + column + "` LIKE '%" + value + "%'";
        return search(query);
    }
}
