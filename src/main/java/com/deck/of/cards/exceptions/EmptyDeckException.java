package com.deck.of.cards.exceptions;

public class EmptyDeckException extends RuntimeException {

    public EmptyDeckException(String message) {
        super(message);
    }
}
