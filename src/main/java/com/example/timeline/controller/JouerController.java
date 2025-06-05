package com.example.timeline.controller;


import com.example.timeline.board.PileOfCards;
import com.example.timeline.board.Player;
import com.example.timeline.board.Timeline;
import com.example.timeline.collection.Card;
import com.example.timeline.collection.Deck;
import com.example.timeline.view.CardViewOnHand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
		Deck deck = new Deck("Test");
		List<Player> players = new ArrayList<>();
		Player player = new Player("Manu");
		players.add(player);
		model = new Timeline(players, deck, 3);
		initUIFromModel();
		setupBoardDropZone();
	}

	private void initUI() {
		playerHand.getChildren().clear();
		selectedCard = null;
	}

	private void initUIFromModel() {
		//titreDeck.setText(model.getDeck().getTitle());
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

	private void displayBoard() {
		board.getChildren().clear();
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
		playerHand.getChildren().clear();
		displayPlayerHand();
	}


	private void setupBoardDropZone() {
		board.setOnDragOver(event -> {
			if (event.getGestureSource() != board && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});

		board.setOnDragDropped(event -> {
			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString() && selectedCard != null) {
				// Ajouter la carte à la fin de la timeline (drop sans cible précise)
				int position = board.getChildren().size();
				model.playTurn(selectedCard, position);
				selectedCard = null;
				refresh();
				success = true;
			}

			event.setDropCompleted(success);
			event.consume();
		});
	}



}
