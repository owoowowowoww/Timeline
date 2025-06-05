package com.example.timeline.io;


import com.example.timeline.collection.Card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public abstract class CardLoader {

	private List<Card> cards;
	private String title;
	
	
	public CardLoader() {
		cards = new ArrayList<>();
	}
	
	public abstract void load();

	public List<Card> getCards() {
		return cards;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
