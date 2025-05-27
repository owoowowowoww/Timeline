package com.example.timeline.board;

public class Player {
    private PileOfCards hand;
    private String name;

    public Player(String name){
        super();
        this.name = name;
        hand = new PileOfCards();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PileOfCards getHand() {
        return hand;
    }
}
