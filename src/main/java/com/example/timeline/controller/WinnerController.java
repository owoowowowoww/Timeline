package com.example.timeline.controller;

import com.example.timeline.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WinnerController {
    public Stage mainStage;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }
    @FXML
    void OnclickQuitter(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void OnclickRelancer(ActionEvent event) throws IOException {
        mainStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Jouer.fxml"));
        Scene jouerScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Jouer");
        mainStage.setScene(jouerScene);
        JouerController controller = fxmlLoader.getController();
        controller.newGameAction();
        controller.setStage(mainStage);
    }


}
