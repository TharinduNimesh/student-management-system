package model;

import java.sql.ResultSet;
import static model.Mysql.search;

public class SpecialClass extends Mysql {

    private int id = 0;
    private String subject;
    private String teacher;
    private String date;
    private String startTime;
    private String endTime;

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void save() throws Exception {
        if (this.id == 0) {
            String query = "INSERT INTO `special_classes`(`teacher_id`, `subject_id`, `date`, `start_time`, `end_time`) VALUES('" + this.teacher + "', '" + this.subject + "', '" + this.date + "', '" + this.startTime + "', '" + this.endTime + "')";
            insert(query);
        }
    }

    public static ResultSet all() throws Exception {
        String query = "SELECT c.*, t.name AS `teacher`, s.name AS `subject` FROM `special_classes` c INNER JOIN subjects s ON s.id = c.subject_id INNER JOIN teachers t ON t.id = c.teacher_id";
        return search(query);
    }
}
