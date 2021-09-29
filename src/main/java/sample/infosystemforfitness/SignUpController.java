package sample.infosystemforfitness;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

public class SignUpController {

    @FXML
    private TextField signUpLogin;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpSurname;

    @FXML
    private TextField signUpPhone;

    @FXML
    private ToolBar signUpToolBar;

    @FXML
    private RadioButton signUpCheckBoxMale;

    @FXML
    private RadioButton signUpCheckBoxFemale;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {

            signUpNewUser();

        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = signUpName.getText();
        String lastName = signUpSurname.getText();
        String userName = signUpLogin.getText();
        String password = signUpPassword.getText();
        String gender = "";
        String phone = signUpPhone.getText();
        if(signUpCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstName, lastName, userName, password,
                gender, phone);

        dbHandler.signUpUser(user);
    }

}
