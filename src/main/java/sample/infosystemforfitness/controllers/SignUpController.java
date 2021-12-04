package sample.infosystemforfitness.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.infosystemforfitness.DatabaseHandler;
import sample.infosystemforfitness.User;

import java.io.IOException;

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
    private Button signInBut;

    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {

            signUpNewUser();

        });

        signInBut.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/sample.fxml");
        });
    }

    public void openNewScene(String window) {
        signInBut.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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



        if(firstName == "" | lastName == "" | userName == "" | password == "" | gender == "" | phone == "" ) {

            a.setAlertType(Alert.AlertType.ERROR);


            a.setContentText("Заполните все поля и укажите Ваш пол!");

            a.show();
        }
        else{
            dbHandler.signUpUser(firstName,lastName,userName,password,gender,phone);
        }
    }
}
