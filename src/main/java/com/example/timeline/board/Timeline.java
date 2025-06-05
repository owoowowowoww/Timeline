package com.example.timeline.board;

import java.util.*;

import com.example.timeline.collection.*;
import com.example.timeline.collection.Collection;

public class Timeline {

    private Collection deck;

    private List<Player> players;
    private PileOfCards drawPile;
    private List<Card> board;
    private int currentPlayerIndex;
    private boolean gameEnded;
    private Map<Player, Integer> scores;

    public Timeline(List<Player> players, Collection deck, int cardsPerPlayer) {
        this.players = players;
        this.deck = deck;
        this.drawPile = new PileOfCards();
        this.board = new LinkedList<>();
        this.currentPlayerIndex = 0;
        this.gameEnded = false;
        scores = new HashMap<>();
        for (Player player : players) {
            scores.put(player, 0);
        }

        for (Card card : deck.getCollection()) {
            drawPile.receiveCard(card);
        }

        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                if (!drawPile.isEmpty()) {
                    player.getHand().receiveCard(drawPile.drawCard());
                }
            }
        }

        if (!drawPile.isEmpty()) {
            board.add(drawPile.drawCard());
        }
    }

    public void playTurn(Card chosenCard, int position) {
        Player currentPlayer = players.get(currentPlayerIndex);
        PileOfCards hand = currentPlayer.getHand();

        if (!hand.contains(chosenCard)) {
            System.out.println("Cette carte n'est pas dans votre main !");
            return;
        }

        if (isCorrectPosition(chosenCard, position)) {
            board.add(position, chosenCard);
            hand.removeCard(chosenCard);

            scores.put(currentPlayer, scores.get(currentPlayer) + 1);

            System.out.println(currentPlayer.getName() + " a correctement placé sa carte.");
        } else {
            System.out.println("Mauvaise position ! Pioche d'une carte.");

            insertCardInTimeline(chosenCard);

            hand.removeCard(chosenCard);

            if (!drawPile.isEmpty()) {
                hand.receiveCard(drawPile.drawCard());
            }
        }

        if (hand.isEmpty()) {
            gameEnded = true;
            showFinalScores();
        } else {
            nextPlayer();
        }
    }

    private boolean isCorrectPosition(Card card, int position) {
        // Vérifie si la carte est bien placée dans la board
        int date = card.getDate();
        if (position == 0) {
            return date <= board.getFirst().getDate();
        } else if (position == board.size()) {
            return date >= board.getLast().getDate();
        } else {
            return date >= board.get(position - 1).getDate()
                    && date <= board.get(position).getDate();
        }
    }

    private void insertCardInTimeline(Card card) {
        int i = 0;
        while (i < board.size() && board.get(i).getDate() < card.getDate()) {
            i++;
        }
        board.add(i, card);
    }

    private void showFinalScores() {
        System.out.println("Scores finaux : ");

        players.stream()
                .sorted((a, b) -> scores.get(b) - scores.get(a))
                .forEach(p -> {
                    System.out.println(p.getName() + " : " + scores.get(p) + " points");
                });
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Card> getTimeline() {
        return board;
    }

    public PileOfCards getPlayerHand() {
        return players.getFirst().getHand();
    }

    public Collection getDeck() {
        return deck;
    }
}