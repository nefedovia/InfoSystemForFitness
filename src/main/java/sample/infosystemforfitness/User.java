package sample.infosystemforfitness;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String gender;
    private String phone;

    public User(String firstName, String lastName, String userName, String password, String gender, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public User() {
    }

=======
>>>>>>> master
=======
>>>>>>> master
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}