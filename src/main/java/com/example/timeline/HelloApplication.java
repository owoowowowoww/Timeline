package com.example.timeline;

import com.example.timeline.Controller.AcceuilController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.timeline.Controller.AcceuilController;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Acceuil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 635);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        AcceuilController controller = fxmlLoader.getController();
        controller.mainStage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}