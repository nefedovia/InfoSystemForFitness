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
import javafx.scene.input.MouseEvent;
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
    private Button refresh;

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
    private ListView<Label> listViewRecord;

    @FXML
    private ListView<Label> listViewPastTrains;



    private int counterOfPassed;

    private int counterOfRecent = 0;

    private int counterOfPayed = 0;

    File file = new File("/home/lasas/project/InfoSystemForFitness/src/main/resources/image.png");

    int bufferPast = 0;
    int bufferRecord = 0;


    DatabaseHandler dbHandler = new DatabaseHandler();


    public void  fillList(){

        listViewPastTrains.getItems().clear();
        listViewRecord.getItems().clear();



        try {
            counterOfPayed = dbHandler.getCountOfPayed();
            counterOfPassed = dbHandler.getCountOfPassed();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSet rs = dbHandler.getRecordTrains();
        ResultSet rs2 = dbHandler.getPastTrains();


        try {
            while (rs2.next()) {

                int id = rs2.getInt(1);
                Label lbl = new Label();
                lbl.setUserData(id);
                lbl.setText(rs2.getString(2) +"   "+ rs2.getTimestamp(3).toString());
                lbl.setOnMouseClicked((MouseEvent e)-> {
                    bufferPast = (int) lbl.getUserData();
                });
                listViewPastTrains.getItems().add(lbl);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {

                int id = rs.getInt(1);
                Label lbl = new Label();
                lbl.setUserData(id);
                lbl.setText(rs.getString(2) +"   "+ rs.getTimestamp(3).toString());
                lbl.setOnMouseClicked((MouseEvent e)-> {
                    bufferRecord = (int) lbl.getUserData();
                });
                listViewRecord.getItems().add(lbl);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Заполнение прошедших тренировок



    }

    public void handleDenyButton(){



                try {
                    dbHandler.denyTraining(String.valueOf(bufferRecord));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


    public void handlePayButton(){


                try {
                    dbHandler.setPayed(String.valueOf(bufferPast));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


    @FXML
    void initialize(){



        Image image = new Image("https://thispersondoesnotexist.com/image");
        imageView.setImage(image);


        trainsPayed.setText(Integer.toString(counterOfPayed));
        trainsPassed.setText(Integer.toString(counterOfPassed));
        trainsRecent.setText(Integer.toString(counterOfRecent));
        name.setText(User.instance().getFirstName() + " " + User.instance().getLastName());



        btnTrains.setOnAction(event -> {
            openNewScene("/sample/infosystemforfitness/trains.fxml");
        });

        payButton.setOnAction(event -> {
            handlePayButton();
        });
        denyButton.setOnAction(event -> {
           handleDenyButton();
        });

        refresh.setOnAction(event -> {
            fillList();
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
