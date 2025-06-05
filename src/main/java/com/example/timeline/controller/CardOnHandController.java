package com.example.timeline.controller;

import com.example.timeline.collection.Card;
import com.example.timeline.util.ImageManager;
import com.example.timeline.view.CardView;
import com.example.timeline.view.CardViewOnBoard;

public class CardOnHandController {

	private JouerController mainController;
	private CardView view;
	private Card controlledCard;

	public CardOnHandController(Card aCard, JouerController controllerMainScreen) {
		this.controlledCard = aCard;
		this.mainController = controllerMainScreen;
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
		mainController.setCardSelectedHand(controlledCard);
	}

	public void setView(CardView cardView) {
		this.view = cardView;
	}

	public Card getControlledCard() {
		return controlledCard;
	}
}
