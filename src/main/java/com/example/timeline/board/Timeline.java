package com.example.timeline.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.timeline.collection.*;

public class Timeline {

    private List<Player> players;
    private PileOfCards drawPile;
    private List<Card> board;
    private int currentPlayerIndex;
    private boolean gameEnded;

    public void Game(List<Player> players, List<Card> allCards, int cardsPerPlayer) {
        this.players = players;
        this.drawPile = new PileOfCards();
        this.board = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.gameEnded = false;

        Collections.shuffle(allCards);
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                player.getHand().receiveCard(allCards.removeFirst());
            }
        }

        for (Card card : allCards) {
            drawPile.receiveCard(card);
        }
    }
}
