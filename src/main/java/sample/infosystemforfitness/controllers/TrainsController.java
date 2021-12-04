package sample.infosystemforfitness.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    private Button btnProfile;

    @FXML
    private Button goToTrain;

    @FXML
    private ListView<String> listView;

    private ObservableList<String> trainsObservableList;

    DatabaseHandler dbHandler = new DatabaseHandler();
    public TrainsController() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        trainsObservableList = FXCollections.observableArrayList();

        ResultSet rs = dbHandler.getTrains();

        while(rs.next()){
            trainsObservableList.add(
                    new String(new StringBuilder().append(rs.getString(1)).append("     ")
                            .append(rs.getString(2)).append("     ")
                            .append(rs.getDate(3).toString().substring(0,14)).toString())
            );
        }

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });

    }

    public void handleRecordButton(){

                listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                String selected = String.valueOf(listView.getSelectionModel().getSelectedItems());
                String[] record = selected.split("     ");

                int index = listView.getSelectionModel().getSelectedIndex();
                listView.getSelectionModel().getSelectedItems().set(index, "Запись сделана");
                try {
                    dbHandler.setTraining(record[0]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       listView.setItems(trainsObservableList);

        goToTrain.setOnAction(event -> {
            handleRecordButton();
        });

        btnHome.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/home.fxml");
        });

        btnProfile.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/profile.fxml");
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
