package com.example.timeline.controller;

import com.example.timeline.Main;
import com.example.timeline.collection.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CollectionController {
    Stage mainStage;

    @FXML
    private VBox vbox;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    void onClickRetour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Acceuil.fxml"));
        Scene acceuilScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Acceuil");
        mainStage.setScene(acceuilScene);
        acceuilScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        AcceuilController controller = fxmlLoader.getController();
        controller.setStage(mainStage);
    }

    private void deckCliked(Deck deck) {
        System.out.println(deck.getTitle());
    }

    @FXML
    public void initialize() {
        for (Deck deck : Deck.getDecks()){
            Label label = new Label();
            label.setText(deck.getTitle());
            label.setOnMouseClicked(event -> {
                deckCliked(deck);
            });
            vbox.getChildren().add(label);
        }
    }
}