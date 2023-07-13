package model;

public class Teacher extends Mysql {

    public Teacher() {
        
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
