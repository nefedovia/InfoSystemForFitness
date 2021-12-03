package sample.infosystemforfitness;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TrainListViewCell extends ListCell<Train> {

    @FXML
    private Label nameOfTrain;

    @FXML
    private Label dayOfWeek;

    @FXML
    private Label time;

    @FXML
    private Button register;

    @FXML
    private AnchorPane paneCell;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Train train, boolean empty) {
        super.updateItem(train, empty);

        if(empty || train == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/sample/infosystemforfitness/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            nameOfTrain.setText(String.valueOf(train.getName()));
            dayOfWeek.setText(train.getDayOfWeek());
            time.setText(train.getTime());
            register.setText(train.getTypeOfAction());



            setText(null);
            setGraphic(paneCell);
        }

    }

}
