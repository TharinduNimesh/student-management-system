package model;

public class Student extends Mysql {

    private int id = 0;
    private String name;
    private String date;
    private String address;
    private String mobile;
    private String email;
    private String gender;

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
