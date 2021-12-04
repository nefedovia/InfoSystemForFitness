module sample.infosystemforfitness {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires mysql.connector.java;


    opens sample.infosystemforfitness to javafx.fxml;
    exports sample.infosystemforfitness;
    exports sample.infosystemforfitness.controllers;
    opens sample.infosystemforfitness.controllers to javafx.fxml;
}