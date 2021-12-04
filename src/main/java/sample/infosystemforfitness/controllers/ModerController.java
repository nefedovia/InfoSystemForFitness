package sample.infosystemforfitness.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.infosystemforfitness.DatabaseHandler;

import java.time.LocalDate;

public class ModerController {

    @FXML
    private Button addTraining;

    @FXML
    private TextField name;

    @FXML
    private TextField time;

    @FXML
    private DatePicker datePicker;

    LocalDate date;
    String dateTime;
    String trainName;

    DatabaseHandler dbHandler = new DatabaseHandler();

    public void addTrain(){

        date = datePicker.getValue();
        dateTime = (date + "-" + time.getText().trim());
        trainName = name.getText();


        dbHandler.addTrain(trainName,dateTime);
    }


    @FXML
    void initialize() {

        addTraining.setOnAction(event -> {
            addTrain();
        });


    }

}
