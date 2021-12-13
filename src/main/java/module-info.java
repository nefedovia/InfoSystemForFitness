module sample.infosystemforfitness {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.testng;


    opens sample.infosystemforfitness to javafx.fxml;
    exports sample.infosystemforfitness;
    exports sample.infosystemforfitness.controllers;
    opens sample.infosystemforfitness.controllers to javafx.fxml;
    exports sample.infosystemforfitness.tests;
    opens sample.infosystemforfitness.tests to javafx.fxml;
}