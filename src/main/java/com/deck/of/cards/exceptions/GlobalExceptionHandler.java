package com.deck.of.cards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DeckNotFoundException.class)
    public ResponseEntity deckNotFound(DeckNotFoundException deckNotFoundException) {
        return new ResponseEntity(deckNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyDeckException.class)
    public ResponseEntity emptyDeck(EmptyDeckException emptyDeckException) {
        return new ResponseEntity(emptyDeckException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DeckOutOfBoundsException.class)
    public ResponseEntity deckNotFound(DeckOutOfBoundsException deckOutOfBoundsException) {
        return new ResponseEntity(deckOutOfBoundsException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CardAlreadyRemoved.class)
    public ResponseEntity cardAlreadyRemoved(CardAlreadyRemoved cardAlreadyRemoved) {
        return new ResponseEntity(cardAlreadyRemoved.getMessage(), HttpStatus.NOT_FOUND);
    }
}
