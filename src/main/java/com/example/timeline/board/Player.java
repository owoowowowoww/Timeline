package com.example.timeline.board;

import com.example.timeline.collection.Card;

public class Player {
    private PileOfCards hand;
    private String name;
    private int score;

    public Player(String name){
        super();
        this.name = name;
        hand = new PileOfCards();
    }

    public String getName() {
        return name;
    }

    public PileOfCards getHand() {
        return hand;
    }

    public void addInHandCard(Card card) {
        hand.addCard(card);
    }

    public boolean hasMoreCardsInHand() {
        return hand.hasMoreCards();
    }

    public void addPoints(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }



}
