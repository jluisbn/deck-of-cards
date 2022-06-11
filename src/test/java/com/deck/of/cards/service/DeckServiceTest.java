package com.deck.of.cards.service;

import com.deck.of.cards.exceptions.DeckNotFoundException;
import com.deck.of.cards.exceptions.DeckOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckServiceTest {

    private DeckService underTest;
    private Integer deckIndex;

    @BeforeEach
    void init() {
        underTest = new DeckService();
        deckIndex = underTest.createDeck();
    }

    @Test
    void getDeckByIdNotFoundTest() {
        var deck = underTest.getDeckById(deckIndex);

        assertNotNull(deck);
    }

    @Test
    void getCardTest() {
        var card = underTest.getNextCard(deckIndex, null);

        assertNotNull(card);
    }

    @Test
    void getCardWithIndexTest() {
        var card = underTest.getNextCard(deckIndex, 10);

        assertNotNull(card);
    }

    @Test
    void getCardWithOutOfBoundsIndexTest() {

        var exception = assertThrows(DeckOutOfBoundsException.class, () -> underTest.getNextCard(deckIndex, 70));

        assertNotNull(exception);
    }

    @Test
    void skipNextCardTest() {
        var index = underTest.skipNextCard(deckIndex, null);

        assertNotNull(index);
        assertEquals(2, index);
    }

    @Test
    void skipNextCardWithIndexTest() {
        var index = underTest.skipNextCard(deckIndex, 10);

        assertNotNull(index);
        assertEquals(11, index);
    }

    @Test
    void getDeckByIdTest() {

        var exception = assertThrows(DeckNotFoundException.class, () -> underTest.getDeckById(10));

        assertNotNull(exception);
    }
}
