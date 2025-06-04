package com.example.timeline.board;

import com.example.timeline.collection.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PileOfCards {
    private Card selectedCard;
    private List<Card> pileOfCards;

    public PileOfCards() {
        super();
        pileOfCards = new ArrayList<>();
        selectedCard = null;
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

    public List<Card> getPileOfCards (){
        return pileOfCards;
    }

    public void addCard(Card card) {
        pileOfCards.add(card);
    }

    public boolean hasMoreCards() {
        return !pileOfCards.isEmpty();
    }

    public Card getSelectedCard() {
        return selectedCard;
    }


}
