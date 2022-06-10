package com.deck.of.cards.service;

import com.deck.of.cards.exceptions.DeckNotFoundException;
import com.deck.of.cards.exceptions.DeckOutOfBoundsException;
import com.deck.of.cards.exceptions.EmptyDeckException;
import com.deck.of.cards.model.Card;
import com.deck.of.cards.model.Deck;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DeckService {

    private final Map<Integer, List<Card>> decks = new HashMap<>();
    private static Integer index = 1;

    public Integer createDeck() {
        decks.put(index, new Deck().getDeck());
        return index++;
    }

    public Card getNextCard(Integer deckId, Integer cardIndex) {
        validateDeckExists(deckId);
        return removeAndGetCard(deckId, cardIndex);
    }

    public Integer skipNextCard(Integer deckId, Integer cardIndex) {
        return 1;
    }

    private Card removeAndGetCard(Integer deckId, Integer cardIndex) {
        var deck = decks.get(deckId);
        validateDeckExists(deckId);

        return getCardByIndex(cardIndex, deck);
    }

    private void validateDeckExists(Integer deckId) {
        if (!decks.containsKey(deckId)) throw new DeckNotFoundException("Deck not found with ID: " + deckId);
    }

    private Card getCardByIndex(Integer cardIndex, List<Card> deck) {

        if (Objects.nonNull(cardIndex)) {

            validateCardIndex(cardIndex);
            var card = deck.get(cardIndex);
            deck.remove(card);
            return card;
        }

        if (deck.size() == 0) throw new EmptyDeckException("Deck is empty, cannot draw or skip a card");

        var card = deck.get(0);
        deck.remove(card);

        return card;
    }

    private void validateCardIndex(Integer cardIndex) {
        if (cardIndex < 0 || cardIndex >= 52) throw new DeckOutOfBoundsException("Index provided is out of bounds");
    }
}
