package com.example.timeline.Controller;


import com.example.timeline.board.PileOfCards;
import com.example.timeline.board.Timeline;
import com.example.timeline.collection.Card;
import com.example.timeline.view.CardViewOnHand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JouerController {

	public Stage mainStage;

	public void setStage(Stage stage) {
		this.mainStage = stage;
	}

	@FXML
	private Label titreDeck;

	@FXML
	private HBox playerHand;

	private Timeline model;

	private Card selectedCard;

	public JouerController() {
		super();
	}

	public void initialization() {
		initUI();
		model = new Timeline();
		initUIFromModel();
	}

	private void initUI() {
		playerHand.getChildren().clear();
		selectedCard = null;
	}

	private void initUIFromModel() {
		titreDeck.setText(model.getDeck().getTitle());
		playerHand.getChildren().clear();
		displayPlayerHand();
	}

	private void refresh() {
		Platform.runLater(() -> {
			initUIFromModel();
		});
	}

	private void displayPlayerHand() {
		PileOfCards hand = model.getPlayerHand();
		for (Card aCard : hand.getPileOfCards()) {
			CardOnHandController controller = new CardOnHandController(aCard, this);
			CardViewOnHand view = new CardViewOnHand(controller, aCard.equals(selectedCard));

			playerHand.getChildren().add(view);
		}
	}
	public void newGameAction() {
		initialization();
	}

	public void setCardSelected(Card controlledCard) {
		selectedCard = controlledCard;
		refresh();
	}

}
