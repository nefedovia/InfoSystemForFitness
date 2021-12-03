package sample.infosystemforfitness;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrainsController implements Initializable {

    @FXML
    private ListView<Train> listView;

    private ObservableList<Train> trainsObservableList;
    public TrainsController() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        trainsObservableList = FXCollections.observableArrayList();

        ResultSet rs = dbHandler.getTrains();

        while(rs.next()){
            trainsObservableList.add(
                    new Train(rs.getString(2),rs.getString(3),rs.getString(4))
            );
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(trainsObservableList);
        listView.setCellFactory(studentListView -> new TrainListViewCell());

    }

}
