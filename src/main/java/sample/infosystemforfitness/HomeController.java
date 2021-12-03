package sample.infosystemforfitness;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btnTrains;

    @FXML
    private Button btnProfile;

    @FXML
    void initialize() {

        btnTrains.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/trains.fxml");
        });

        btnProfile.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/profile.fxml");
        });
    }

    public void openNewScene(String window) {
        btnProfile.getScene().getWindow().hide();

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
        stage.showAndWait();
    }
    

}
