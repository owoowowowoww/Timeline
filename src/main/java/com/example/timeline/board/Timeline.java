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
            System.out.println(currentPlayer.getName() + " a correctement placé sa carte.");
        } else {
            System.out.println("Mauvaise position ! Pioche d'une carte.");

            // Ajouter la carte dans la timeline à la bonne place
            insertCardInTimeline(chosenCard);

            // Retirer la carte de la main du joueur
            hand.removeCard(chosenCard);

            // Le joueur pioche une carte
            if (!drawPile.isEmpty()) {
                hand.receiveCard(drawPile.drawCard());
            }
        }

        // Vérifier la victoire
        if (hand.isEmpty()) {
            gameEnded = true;
            System.out.println(currentPlayer.getName() + " a gagné !");
        } else {
            nextPlayer();
        }
    }

    private boolean isCorrectPosition(Card card, int position) {
        // Vérifie si la carte est bien placée dans la board
        int date = card.getDate();
        if (position == 0) {
            return date <= board.get(0).getDate();
        } else if (position == board.size()) {
            return date >= board.get(board.size() - 1).getDate();
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
}
