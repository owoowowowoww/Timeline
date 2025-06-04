package com.example.timeline.collection;

import com.example.timeline.io.CardLoader;
import com.example.timeline.io.FAKECardLoader;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Collection {
    private String title;
    private List<Card> collection;

    public Collection() {
        super();
        collection = new LinkedList<>();
        setup();
    }

    private void setup() {
        CardLoader loader = new FAKECardLoader();
        loader.load();
        collection = loader.getCards();
        title = loader.getTitle();
        Collections.shuffle(collection);
    }

    public void addCard(Card card) {
        if (!collection.contains(card))
            collection.add(card);
    }

    public void removeCard(Card card){
        collection.remove(card);
    }

    public List<Card> getCollection() {
        return collection;
    }

    public Card drawCard() {
        if (collection.isEmpty()) {
            return null;
        }
        return collection.remove(0);
    }

    public boolean hasMoreCards() {
        return !collection.isEmpty();
    }

    public String getTitle() {
        return title;
    }
}
