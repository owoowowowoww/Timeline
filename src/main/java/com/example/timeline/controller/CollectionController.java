package com.example.timeline.controller;

import com.example.timeline.Main;
import com.example.timeline.collection.Card;
import com.example.timeline.collection.Deck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CollectionController {

    @FXML
    private TextField cardDescriptionField;

    @FXML
    private TextField cardDateField;

    @FXML
    private TextField cardImageField;

    @FXML
    private TextField cardTitleField;

    @FXML
    private ComboBox<Card> cards;

    @FXML
    private TextField deckField;

    @FXML
    private ComboBox<Deck> decks;

    Stage mainStage;

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

    private void initDecks() {
        decks.getItems().clear();
        for (Deck deck : Deck.getDecks()) {
            System.out.println(deck);
            decks.getItems().add(deck);
        }
    }

    private void initCards() {
        Deck selected = decks.getValue();
        cards.getItems().clear();
        if (selected == null) return;
        for (Card card : selected.getCards()) {
            cards.getItems().add(card);
        }
    }

    @FXML
    public void initialize() {
        initDecks();

        decks.setOnAction(event -> {
            Deck selected = decks.getValue();
            if(selected == null) return;
            _clearDeckFields();
            deckField.setText(selected.getTitle());
            initCards();
        });

        cards.setOnAction(event -> {
            Card selected = cards.getValue();
            if(selected == null) return;
            clearCardFields();
            cardTitleField.setText(selected.getTitle());
            cardDateField.setText(Integer.toString(selected.getDate()));
            cardImageField.setText(selected.getImage());
            cardDescriptionField.setText(selected.getDescription());
        });

    }

    @FXML
    void addCard(ActionEvent event) {
        String title = cardTitleField.getText();
        int date = Integer.parseInt(cardDateField.getText());
        String image = cardImageField.getText();
        String description = cardDescriptionField.getText();

        Deck selected = decks.getValue();
        if(selected == null) return;
        selected.addCarte(new Card(title, date, image, description));
        clearCardFields();
        initCards();
    }

    @FXML
    void addDeck(ActionEvent event) {
        String title = deckField.getText();
        new Deck(title);
        clearDeckFields();
        initDecks();
    }

    @FXML
    void deleteCard(ActionEvent event) {
        Deck selectedDeck = decks.getValue();
        Card selectedCard = cards.getValue();
        if(selectedDeck == null || selectedCard == null) return;
        selectedDeck.getCards().removeIf(card -> card.getTitle().equals(selectedCard.getTitle()));
        clearCardFields();
        initCards();
    }

    @FXML
    void deleteDeck(ActionEvent event) {
        Deck selectedDeck = decks.getValue();
        if(selectedDeck == null) return;
        Deck.getDecks().removeIf(deck -> deck.getTitle().equals(selectedDeck.getTitle()));
        clearDeckFields();
        initDecks();
    }

    @FXML
    void editCard(ActionEvent event) {
        String title = cardTitleField.getText();
        int date = Integer.parseInt(cardDateField.getText());
        String image = cardImageField.getText();
        String description = cardDescriptionField.getText();

        Card selected  = cards.getValue();
        if(selected == null) return;
        selected.setTitle(title);
        selected.setDate(date);
        selected.setImage(image);
        selected.setDescription(description);
        clearCardFields();
        initCards();
    }

    @FXML
    void editDeck(ActionEvent event) {
        String title = deckField.getText();
        Deck selected = decks.getValue();
        if(selected == null) return;
        selected.setTitle(title);
        clearDeckFields();
        initDecks();
    }

    @FXML
    void save(ActionEvent event) {
        Deck.save("data/data.json");
    }

    private void clearCardFields() {
        cards.setValue(null);
        _clearCardFields();
    }

    private void _clearCardFields() {
        cardDescriptionField.clear();
        cardDateField.clear();
        cardImageField.clear();
        cardTitleField.clear();
    }

    private void clearDeckFields() {
        decks.setValue(null);
        _clearDeckFields();
    }

    private void _clearDeckFields() {
        deckField.clear();
        clearCardFields();
    }

}
