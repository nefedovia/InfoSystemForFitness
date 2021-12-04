package sample.infosystemforfitness;

public class User {
    private  String firstName;
    private  String lastName;
    private  String userName;
    private String password;
    private  String gender;
    private  String phone;
    private  String role;
    private  int id;
    private static User user;

    public User(String firstName, String lastName, String userName, String password, String gender, String phone, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.role = role;
    }

    public static User instance(){
        if(user == null){
            user = new User("","","","","","","");
        }
        return user;
    }
    public static void init(String firstName, String lastName, String userName, String password,  String gender,  String phone , String role){
        if(user == null){
            user = new User(firstName,lastName,userName,password,gender,phone,role);
        }
    }












    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public String getLastName() {
        return lastName;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }
}