package com.example.timeline.controller;

import com.example.timeline.Main;
import com.example.timeline.collection.Card;
import com.example.timeline.collection.Deck;
import com.example.timeline.view.CardViewOnBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ParametrePartieController {
    private static final int NB_JOUEUR = 1;
    private int nbJoueur = 0;
    public Stage mainStage;

    private Deck selectedDeck;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private HBox decks;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private TextField timeAnwser;

    public void setStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    void onClickSetNbJoueur(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String val = sourceButton.getText();
        if (val.equals("1 joueur")) {
            nbJoueur = 1;
        }
        if (val.equals("2 joueurs")) {
            nbJoueur = 2;
        }
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    @FXML
    void onClickJouer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Jouer.fxml"));
        Scene jouerScene = new Scene(fxmlLoader.load(), 950, 635);
        mainStage.setTitle("Jeux");
        mainStage.setScene(jouerScene);
        jouerScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        JouerController controller = fxmlLoader.getController();
        controller.setDeck(selectedDeck);
        controller.setStage(mainStage);
        if (timeAnwser.getText().isEmpty()) {
            controller.setSeconds(30);
        } else {
            controller.setSeconds(Integer.parseInt(timeAnwser.getText()));
        }
        if (nbJoueur == 0) {
            controller.setNbJoueur(NB_JOUEUR);
        } else {
            controller.setNbJoueur(nbJoueur);
        }
        controller.initialization();
    }

    public int getTimeAnswer() {
        return Integer.parseInt(timeAnwser.getText());
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

    @FXML
    public void initialize() {
        displaydecks(); // affiche les decks visuellement
        scrollpane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double deltaY = event.getDeltaY();
            double speed = 5;

            scrollpane.setHvalue(scrollpane.getHvalue() - (deltaY * speed) / scrollpane.getWidth());
            event.consume();
        });
    }

    private void displaydecks() {
        decks.getChildren().clear();

        for (Deck aDeck : Deck.getDecks()) {
            if (!aDeck.getCards().isEmpty()) {
                Card firstCard = aDeck.getCards().getFirst();
                CardOnHandController controller = new CardOnHandController(firstCard, this);
                CardViewOnBoard view = new CardViewOnBoard(controller);
                decks.getChildren().add(view);
            }
        }
    }


    private void deckCliked(Deck deck) {
        selectedDeck = deck;
        System.out.println("Deck sélectionné : " + deck.getTitle());

        for (javafx.scene.Node node : decks.getChildren()) {
            if (node instanceof Label label) {
                label.setStyle(""); // reset
                if (label.getText().equals(deck.getTitle())) {
                    label.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                }
            }
        }
    }

    public void setSelectedDeck(Deck deck) {
        deckCliked(deck);
    }

}
