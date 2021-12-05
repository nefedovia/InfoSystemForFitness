package sample.infosystemforfitness.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.infosystemforfitness.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrainsController implements Initializable {

    @FXML
    private Button btnHome;

    @FXML
    private Button refresh;

    @FXML
    private Button btnProfile;

    @FXML
    private Button goToTrain;

    @FXML
    private ListView<Label> listView;

    private ObservableList<String> trainsObservableList;

    DatabaseHandler dbHandler = new DatabaseHandler();

    int buffer = 0;




    @FXML
    public void fillList() {

        ResultSet rs = dbHandler.getTrains();


        try {
            while (rs.next()) {

                int id = rs.getInt(1);
                 Label lbl = new Label();
                lbl.setText(rs.getString(2) + rs.getDate(3).toString());
                lbl.setOnMouseClicked((MouseEvent e)-> {
                   buffer = id;
                });
                listView.getItems().add(lbl);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void handleRecordButton(){
try {
    if(buffer == 0) {
        dbHandler.setTraining(String.valueOf(buffer));
    }
} catch (SQLException e) {
    e.printStackTrace();
}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        goToTrain.setOnAction(event -> {


                handleRecordButton();


        });

        btnHome.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/home.fxml");
        });

        btnProfile.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/profile.fxml");
        });

        refresh.setOnAction(event -> {
           fillList();
        });
    }

    public void openNewScene(String window) {
        btnHome.getScene().getWindow().hide();

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
