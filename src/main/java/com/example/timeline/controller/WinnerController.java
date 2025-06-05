package com.example.timeline.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

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
    void OnclickRelancer(ActionEvent event) {
        mainStage.close();
        JouerController controller = new JouerController();
        controller.newGameAction();
    }


}
