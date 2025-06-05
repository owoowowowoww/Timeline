package com.example.timeline.controller;


import com.example.timeline.Main;
import com.example.timeline.board.PileOfCards;
import com.example.timeline.board.Player;
import com.example.timeline.board.Timeline;
import com.example.timeline.collection.Card;
import com.example.timeline.collection.Collection;
import com.example.timeline.view.CardViewOnHand;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JouerController {

	public Stage mainStage;

	public void setStage(Stage stage) {
		this.mainStage = stage;
	}

	@FXML
	private Label titreDeck;

	@FXML
	private HBox playerHand;

	@FXML
	private HBox board;

	private Timeline model;

	private Card selectedCard;

	public JouerController() {
		super();
	}

	public void initialization() {
		initUI();
		Collection deck = new Collection();
		List<Player> players = new ArrayList<>();
		Player player = new Player("Manu");
		players.add(player);
		model = new Timeline(players, deck, 3);
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
		displayBoard();
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

}

	private void displayBoard() {
		List<Card> board = model.getTimeline();
		if (board == null) {
			System.err.println("ERREUR : le champ 'board' n’a pas été injecté ! Vérifie le fx:id dans le FXML.");
			return;
		}
		System.out.println("Nb cartes sur la timeline : " + board.size());
		for (Card aCard : board) {
			CardOnHandController controller = new CardOnHandController(aCard, this);
			CardViewOnHand view = new CardViewOnHand(controller, aCard.equals(selectedCard));

			this.board.getChildren().add(view);
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
