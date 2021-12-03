package sample.infosystemforfitness;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

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
    private ListView<Train> listView;

    private ObservableList<Train> trainsObservableList;
    public TrainsController() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        trainsObservableList = FXCollections.observableArrayList();

        ResultSet rs = dbHandler.getTrains();

        while(rs.next()){
            trainsObservableList.add(
                    new Train(rs.getString(2),rs.getString(3),rs.getString(4),"Записаться")
            );
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(trainsObservableList);
        listView.setCellFactory(trainsListView -> new TrainListViewCell());


        btnHome.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/home.fxml");
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
