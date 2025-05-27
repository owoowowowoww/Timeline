package com.example.timeline.collection;

import java.util.LinkedList;
import java.util.List;

public class Collection {
    private List<Card> collection;

    public Collection() {
        super();
        collection = new LinkedList<>();
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
}
