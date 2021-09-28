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
    }

}
