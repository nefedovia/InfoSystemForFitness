package sample.infosystemforfitness;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("trains.fxml"));
        primaryStage.setTitle("InfoSystem");
        primaryStage.setScene(new Scene(root,1000 , 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}