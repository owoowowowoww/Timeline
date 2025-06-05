package com.example.timeline.controller;

import com.example.timeline.collection.Card;
import com.example.timeline.collection.Deck;
import com.example.timeline.util.ImageManager;
import com.example.timeline.view.CardView;
import com.example.timeline.view.CardViewOnBoard;

public class CardOnHandController {

	private JouerController mainController;
	private ParametrePartieController mainControllerParam;
	private CardView view;
	private Card controlledCard;

	public CardOnHandController(Card aCard, JouerController controllerMainScreen) {
		this.controlledCard = aCard;
		this.mainController = controllerMainScreen;
	}

	public CardOnHandController(Card aCard, ParametrePartieController controllerMainScreen) {
		this.controlledCard = aCard;
		this.mainControllerParam = controllerMainScreen;
	}

	public void initView() {
		if (view != null) {
			view.setTitle(controlledCard.getTitle());
			view.setCardImage(ImageManager.getInstance().getImage(controlledCard.getImage()));

			if (view instanceof CardViewOnBoard boardView) {
				boardView.setCardDate(controlledCard.getDate());
			}
		}
	}

	public void selectAction() {
		if (mainController != null) {
			mainController.setCardSelectedHand(controlledCard);
		} else if (mainControllerParam != null) {
			for (Deck deck : Deck.getDecks()) {
				if (deck.getCards().contains(controlledCard)) {
					mainControllerParam.setSelectedDeck(deck);
					break;
				}
			}
		} else {
			System.err.println("Aucun contrôleur principal n'est défini dans CardOnHandController.");
		}
	}

	public void setView(CardView cardView) {
		this.view = cardView;
	}

	public Card getControlledCard() {
		return controlledCard;
	}
}
