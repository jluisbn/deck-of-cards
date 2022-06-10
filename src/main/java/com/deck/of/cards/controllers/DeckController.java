package com.deck.of.cards.controllers;

import com.deck.of.cards.model.Card;
import com.deck.of.cards.service.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @PostMapping(value = "/decks", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createDeck() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("deckId", deckService.createDeck()));
    }

    @GetMapping("/decks")
    @ResponseBody
    public Card getNextCard(@RequestParam Integer deckId, Integer cardIndex) {
        return deckService.getNextCard(deckId, cardIndex);
    }

    @PutMapping("/decks")
    public Integer skipNextCard(@PathVariable Integer deckId, Integer cardIndex) {
        return deckService.skipNextCard(deckId, cardIndex);
    }
}
