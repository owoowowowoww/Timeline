module com.example.timeline {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timeline to javafx.fxml;
    exports com.example.timeline;
    exports com.example.timeline.Controller;
    opens com.example.timeline.Controller to javafx.fxml;
}