module sample.infosystemforfitness {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.infosystemforfitness to javafx.fxml;
    exports sample.infosystemforfitness;
}