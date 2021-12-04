package sample.infosystemforfitness.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import animation.Shake;
import sample.infosystemforfitness.DatabaseHandler;
import sample.infosystemforfitness.User;

public class Controller {
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button authSignInButton;

    @FXML
    private Label error;

    @FXML
    void initialize() {

        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            }else{
                loginUser(loginText,loginPassword);
            }
        });

        signUpButton.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getUser(loginText,loginPassword);

        int counter = 0;

        try {
            while (result.next()) {
                counter++;

            }
        } catch (SQLException e) {
                e.printStackTrace();
            }

        if (counter >= 1) {
            try {
                while (result.next()) {
                   User.init(result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(User.instance().getRole() == "user") {
                openNewScene("/sample/infosystemforfitness/home.fxml");
            }   else    {
                openNewScene("/sample/infosystemforfitness/moder.fxml");
            }

        } else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPassAnim = new Shake(passField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    public void openNewScene(String window) {
        authSignInButton.getScene().getWindow().hide();

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
}
