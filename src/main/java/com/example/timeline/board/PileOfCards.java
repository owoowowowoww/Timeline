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
}
