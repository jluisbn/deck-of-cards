package com.deck.of.cards.exceptions;

public class CardAlreadyRemoved extends RuntimeException {

    public CardAlreadyRemoved(String message) {
        super(message);
    }
}
