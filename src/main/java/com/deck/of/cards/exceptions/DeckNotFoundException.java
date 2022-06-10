package com.deck.of.cards.exceptions;

public class DeckNotFoundException extends RuntimeException {

    public DeckNotFoundException(String message) {
        super(message);
    }
}
