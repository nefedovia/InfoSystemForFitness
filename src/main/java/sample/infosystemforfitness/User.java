package sample.infosystemforfitness;

public class User {
    private static String firstName;
    private static String lastName;
    private static String userName;
    private String password;
    private static String gender;
    private static String phone;
    private static String role;
    private static int id;
    private static User user;

    public User(String firstName, String lastName, String userName, String password, String gender, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
    }

    public static User instance(){
        if(user == null){
            user = new User("","","","","","");
        }
        return user;
    }


    public static void init(String firstName, String lastName, String userName, String password,  String gender,  String phone , String role){
        if(user == null){
            user = new User(firstName,lastName,userName,password,gender,phone);
        }
    }


    public User() {
    }



    public String getFirstName() {
        return firstName;
    }

    public String getRole() {        return role;    }

    public void setRole(String role) {        this.role = role;    }

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