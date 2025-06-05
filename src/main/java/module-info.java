module com.example.timeline {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.timeline to javafx.fxml;
    exports com.example.timeline;
    exports com.example.timeline.controller;
    opens com.example.timeline.controller to javafx.fxml;
}