package sample.infosystemforfitness.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.infosystemforfitness.DatabaseHandler;
import sample.infosystemforfitness.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnTrains;

    @FXML
    private Button denyButton;

    @FXML
    private Button payButton;


    @FXML
    private Label trainsPassed;

    @FXML
    private Label trainsRecent;

    @FXML
    private Label trainsPayed;

    @FXML
    private Label name;

    @FXML
    private ImageView imageView ;

    @FXML
    private ListView<String> listViewRecord;

    @FXML
    private ListView<String> listViewPastTrains;

    private ObservableList<String> trainsRecordObservableList;

    private ObservableList<String> trainsPastObservableList;

    private int counterOfPassed;

    private int counterOfRecent = 0;

    private int counterOfPayed = 0;

    File file = new File("/home/lasas/project/InfoSystemForFitness/src/main/resources/image.png");


    DatabaseHandler dbHandler = new DatabaseHandler();


    public ProfileController() throws SQLException {
        //Заполнение тренировок на которые записан

        counterOfPassed = dbHandler.getCountOfPassed();
        counterOfPayed = dbHandler.getCountOfPayed();



        //Заполнение прошедших тренировок



    }

    public void handleDenyButton(){


                listViewRecord.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                String selected = String.valueOf(listViewRecord.getSelectionModel().getSelectedItems());
                String[] record = selected.split("     ");
                int index = listViewRecord.getSelectionModel().getSelectedIndex();
                listViewRecord.getSelectionModel().getSelectedItems().set(index, "Отменено");
                try {
                    dbHandler.denyTraining(record[0]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


    public void handlePayButton(){

                listViewPastTrains.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                String selected = String.valueOf(listViewPastTrains.getSelectionModel().getSelectedItems());
                String[] record = selected.split("     ");

                int index = listViewPastTrains.getSelectionModel().getSelectedIndex();
                listViewPastTrains.getSelectionModel().getSelectedItems().set(index, "Оплачено");
                try {
                    dbHandler.setPayed(record[0]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


    @FXML
    void initialize() throws MalformedURLException, SQLException {

        trainsPastObservableList = FXCollections.observableArrayList();

        ResultSet rs2 = dbHandler.getPastTrains();
        try {
            while (rs2.next()) {

                trainsPastObservableList.add(
                        new String(new StringBuilder().append(rs2.getString(1)).append("     ")
                                .append(rs2.getString(2)).append("     ")
                                .append(rs2.getDate(3).toString().substring(0, 14)).toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        trainsRecordObservableList = FXCollections.observableArrayList();
        ResultSet rs = dbHandler.getRecordTrains();
        try {
            while (rs.next()) {
                counterOfRecent++;
                trainsRecordObservableList.add(

                        new String(new StringBuilder().append(rs.getString(1)).append("     ")
                                .append(rs.getString(2)).append("     ")
                                .append(rs.getDate(3).toString().substring(0, 14)).toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Image image = new Image("https://thispersondoesnotexist.com/image");
        imageView.setImage(image);


        trainsPayed.setText(Integer.toString(counterOfPayed));
        trainsPassed.setText(Integer.toString(counterOfPassed));
        trainsRecent.setText(Integer.toString(counterOfRecent));
        name.setText(User.instance().getFirstName() + " " + User.instance().getLastName());

        listViewRecord.setItems(trainsRecordObservableList);

        listViewPastTrains.setItems(trainsPastObservableList);

        btnTrains.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/trains.fxml");
        });

        payButton.setOnAction(event -> {
//            handlePayButton();
        });
        denyButton.setOnAction(event -> {
//            handleDenyButton();
        });

        btnHome.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/home.fxml");
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
