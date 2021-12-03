package sample.infosystemforfitness;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnTrains;

    @FXML
    private Label trainsPassed;

    @FXML
    private Label trainsRecent;

    @FXML
    private Label trainsDeclined;

    @FXML
    private ListView<Train> listViewRecord;

    @FXML
    private ListView<Train> listViewPastTrains;

    private ObservableList<Train> trainsRecordObservableList;

    private ObservableList<Train> trainsPastObservableList;

    private int counterOfPassed = 0;

    private int counterOfRecent = 0;


    public ProfileController() throws SQLException {
        //Заполнение тренировок на которые записан
        DatabaseHandler dbHandler = new DatabaseHandler();
        trainsRecordObservableList = FXCollections.observableArrayList();

        ResultSet rs = dbHandler.getRecordTrains();

        while(rs.next()){
            counterOfRecent++;
            trainsRecordObservableList.add(
                    new Train(rs.getString(2),rs.getString(3),rs.getString(4),"Отменить")
            );
        }
        //Заполнение прошедших тренировок
        trainsPastObservableList = FXCollections.observableArrayList();

        ResultSet rs2 = dbHandler.getPastTrains();

        while(rs2.next()){
            counterOfPassed++;
            trainsPastObservableList.add(
                    new Train(rs.getString(2),rs.getString(3),rs.getString(4),"Заплатить")
            );
        }






    }


    @FXML
    void initialize() {

        trainsPassed.setText(Integer.toString(counterOfPassed));
        trainsRecent.setText(Integer.toString(counterOfRecent));

        listViewRecord.setItems(trainsRecordObservableList);
        listViewRecord.setCellFactory(trainsListView -> new TrainListViewCell());

        listViewPastTrains.setItems(trainsPastObservableList);
        listViewPastTrains.setCellFactory(trainsListView -> new TrainListViewCell());

        btnTrains.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/trains.fxml");
        });

        btnHome.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/home.fxml");
        });
    }

    public void openNewScene(String window) {
        btnTrains.getScene().getWindow().hide();

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
