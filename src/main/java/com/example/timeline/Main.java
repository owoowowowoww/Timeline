package com.example.timeline;

import com.example.timeline.Controller.AcceuilController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Acceuil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 635);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        AcceuilController controller = fxmlLoader.getController();
        controller.mainStage = stage;
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}