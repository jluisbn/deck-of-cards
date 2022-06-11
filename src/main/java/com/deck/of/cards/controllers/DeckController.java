package com.deck.of.cards.controllers;

import com.deck.of.cards.model.Card;
import com.deck.of.cards.service.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
    @ResponseBody
    public Integer skipNextCard(@RequestParam Integer deckId, Integer cardIndex) {
        return deckService.skipNextCard(deckId, cardIndex);
    }

    @GetMapping("/decks/{id}")
    @ResponseBody
    public List<Card> getDeckById(@PathVariable Integer id) {
        return deckService.getDeckById(id);
    }
}
