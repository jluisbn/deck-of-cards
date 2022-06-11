package com.deck.of.cards.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Deck {

    private final List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        init();
    }

    private void init() {

        int CARDS_PER_TYPE = 13;
        int index = 0;

        for (int i = 1; i <= CARDS_PER_TYPE; i++) {
            for (TypeOfCard type : TypeOfCard.values()) {
                deck.add(new Card(i, index++, type));
            }
        }
    }
}
