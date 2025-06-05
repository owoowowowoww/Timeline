package com.example.timeline.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DescriptionCardController {
    @FXML
    private Label description;

    public Stage mainStage;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    public void setDescription(String selectedCardDescription){
        description.setText(selectedCardDescription);
    }
}
