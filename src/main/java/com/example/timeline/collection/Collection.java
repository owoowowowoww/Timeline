package com.example.timeline.collection;

import com.example.timeline.io.CardLoader;
import com.example.timeline.io.FAKECardLoader;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Collection {
    private String title;
    private Deque<Card> collection;

    public Collection() {
        super();
        collection = new LinkedList<>();
        setup();
    }

    private void setup() {
        CardLoader loader = new FAKECardLoader();
        loader.load();
        List<Card> cards = loader.getCards();
        Collections.shuffle(cards);
        collection.addAll(cards);
        title = loader.getTitle();
    }

    public void addCard(Card card) {
        if (!collection.contains(card))
            collection.add(card);
    }

    public void removeCard(Card card){
        collection.remove(card);
    }

    public Deque<Card> getCollection() {
        return collection;
    }

    public Card drawCard() {
        if (collection.isEmpty()) {
            return null;
        }
        return collection.pollFirst();
    }

    public boolean hasMoreCards() {
        return !collection.isEmpty();
    }

    public String getTitle() {
        return title;
    }
}
