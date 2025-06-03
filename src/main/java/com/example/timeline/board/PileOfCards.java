package com.example.timeline.board;

import com.example.timeline.collection.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PileOfCards {
    private List<Card> pileOfCards;

    public PileOfCards() {
        super();
        pileOfCards = new ArrayList<>();
    }

    public void receiveCard(Card newCard) {
        pileOfCards.add(newCard);
    }

    public boolean isEmpty() {
        return pileOfCards.isEmpty();
    }

    public Card drawCard() {
        return pileOfCards.removeFirst();
    }

    public boolean contains(Card c) {
        return pileOfCards.contains(c);
    }

    public void removeCard(Card c) {
        pileOfCards.remove(c);
    }
}
