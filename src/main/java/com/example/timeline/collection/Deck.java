package com.example.timeline.collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Deck { // nouveau deck

    private static JsonNode jeux;
    private static ObjectMapper mapper = new ObjectMapper();
    private static List<Deck> decks = new ArrayList<Deck>();
    private Deque<Card> cards;
    private String title;

    public static void load(String file){
        try {
            jeux = mapper.readTree(new File(file)).get("jeux");

            Iterator<String> fieldNames = jeux.fieldNames();
            while (fieldNames.hasNext()) {
                String key = fieldNames.next();
                System.out.println(key);
                new Deck(key);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(String file){
        try {
            File f = new File(file);
            JsonNode jsonNode = mapper.readTree(f);
            ObjectNode jeuxObjectNode = (ObjectNode) jsonNode.get("jeux");


            for (Deck j : decks) {
                ObjectNode objectNode = (ObjectNode) jeuxObjectNode.get(j.title);
                if (objectNode == null) {
                    objectNode = mapper.createObjectNode();
                    jeuxObjectNode.set(j.title, objectNode);
                }
                JsonNode cartesNode = mapper.valueToTree(j.cards);
                objectNode.set("cartes", cartesNode);

            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(f, jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Deck(String title){
        this.title = title;
        decks.add(this);

        if (jeux.get(title) != null) {
            JsonNode cartesNode = jeux.get(title).get("cartes");

            try {
                List<Card> temp = mapper.readValue(cartesNode.toString(), new TypeReference<List<Card>>() {});
                cards = new LinkedList<>(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            cards = new LinkedList<>();
        }
    }

    public static Deck getDeck(String title) {
        for (Deck deck : decks) {
            if (deck.getTitle().equals(title)) {
                return deck;
            }
        }
        return null;
    }

    public static List<Deck> getDecks() {
        return decks;
    }

    public Deque<Card> getCards(){
        return cards;
    }

    public void addCarte(Card card){
        cards.add(card);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public String getTitle(){
        return title;
    }

    public Card drawCard() {
        if (isEmpty())
            return null;
        return cards.removeFirst();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

}
