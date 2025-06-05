	package com.example.timeline.controller;


	import com.example.timeline.collection.Card;
	import com.example.timeline.util.ImageManager;
	import com.example.timeline.view.CardViewOnHand;

	public class CardOnHandController {

	private JouerController mainController;
	private CardViewOnHand view;
	private Card controlledCard;


	public CardOnHandController(Card aCard, JouerController controllerMainScreen) {
		this.controlledCard = aCard;
		mainController = controllerMainScreen;
	}

	public void initView() {
		view.setTitle(controlledCard.getTitle());
		view.setCardImage(ImageManager.getInstance().getImage(controlledCard.getImage()));
	}

	public void selectAction() {
		mainController.setCardSelected(controlledCard);
	}

	public void setView(CardViewOnHand cardViewOnHand) {
		view = cardViewOnHand;
	}
	
	
	
}
