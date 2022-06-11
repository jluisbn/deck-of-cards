package com.deck.of.cards.controller;

import com.deck.of.cards.controllers.DeckController;
import com.deck.of.cards.model.Card;
import com.deck.of.cards.service.DeckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeckControllerTest {

    private DeckController underTest;

    private final DeckService deckService = mock(DeckService.class);

    @BeforeEach
    void init() {
        underTest = new DeckController(deckService);
    }

    @Test
    void createDeckTest() {
        var deck = underTest.createDeck();

        assertNotNull(deck);
    }

    @Test
    void getNextCardTest() {

        Card card = mock(Card.class);
        when(deckService.getNextCard(anyInt(), anyInt())).thenReturn(card);
        var res = underTest.getNextCard(1, 1);

        assertNotNull(res);
    }

    @Test
    void skipCardTest() {

        when(deckService.skipNextCard(anyInt(), anyInt())).thenReturn(1);
        var res = underTest.skipNextCard(1, 1);

        assertNotNull(res);
    }

    @Test
    void getDeckByIdTest() {

        Card card = mock(Card.class);
        when(deckService.getDeckById(anyInt())).thenReturn(List.of(card));

        var res = underTest.getDeckById(1);

        assertNotNull(res);
    }
}
