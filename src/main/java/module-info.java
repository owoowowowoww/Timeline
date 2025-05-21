module com.example.timeline {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timeline to javafx.fxml;
    exports com.example.timeline;
}