package com.deck.of.cards.model;

import lombok.Getter;

@Getter
public class Card {

    int value;
    int cardIndex;
    TypeOfCard type;

    public Card(int value, int cardIndex, TypeOfCard type) {
        this.value = value;
        this.cardIndex = cardIndex;
        this.type = type;
    }
}
