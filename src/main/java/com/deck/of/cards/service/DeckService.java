package com.deck.of.cards.service;

import com.deck.of.cards.exceptions.CardAlreadyRemoved;
import com.deck.of.cards.exceptions.DeckNotFoundException;
import com.deck.of.cards.exceptions.DeckOutOfBoundsException;
import com.deck.of.cards.exceptions.EmptyDeckException;
import com.deck.of.cards.model.Card;
import com.deck.of.cards.model.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DeckService {

    private final Map<Integer, List<Card>> decks = new HashMap<>();
    private static Integer index = 1;
    private static Integer deckIndex = 1;

    Logger logger = LoggerFactory.getLogger(DeckService.class);

    public Integer createDeck() {
        logger.info("Creating deck");

        decks.put(index, new Deck().getDeck());
        return index++;
    }

    public List<Card> getDeckById(Integer deckId) {
        logger.info("Getting deck with ID: {}", deckId);

        validateDeckExists(deckId);
        return decks.get(deckId);
    }

    public Card getNextCard(Integer deckId, Integer cardIndex) {

        logger.info("Getting next card from deck with ID {}", deckId);

        validateDeckExists(deckId);
        return removeAndGetCard(deckId, cardIndex, false);
    }

    public Integer skipNextCard(Integer deckId, Integer cardIndex) {

        logger.info("Skipping card from deck with ID {}", deckId);

        validateDeckExists(deckId);
        var card = removeAndGetCard(deckId, cardIndex, true);
        return card.getCardIndex() + 1;
    }

    private Card removeAndGetCard(Integer deckId, Integer cardIndex, boolean skip) {
        var deck = decks.get(deckId);
        validateDeckExists(deckId);

        return getCardByIndex(cardIndex, deck, skip);
    }

    private void validateDeckExists(Integer deckId) {
        if (!decks.containsKey(deckId)) throw new DeckNotFoundException("Deck not found with ID: " + deckId);
    }

    private Card getCardByIndex(Integer cardIndex, List<Card> deck, boolean skip) {

        if (deck.size() == 0) throw new EmptyDeckException("Deck is empty, cannot draw or skip a card");

        if (Objects.nonNull(cardIndex)) {

            validateCardIndex(cardIndex);

            var card = getCardByIndexSafe(deck, cardIndex);
            deck.remove(card.getCardIndex());
            return card;
        }

        Card card;

        if (skip) card = getCardByIndexSafe(deck, deckIndex);
        else card = deck.get(0);

        deck.remove(card);
        deckIndex = card.getCardIndex();

        return card;
    }

    private void validateCardIndex(Integer cardIndex) {
        if (cardIndex < 0 || cardIndex >= 52) throw new DeckOutOfBoundsException("Index provided is out of bounds");
    }

    private Card getCardByIndexSafe(List<Card> deck, Integer cardIndex) {
        try {
            var card = deck.get(cardIndex);
            deck.remove(card);
            deckIndex = card.getCardIndex();
            return card;
        } catch (IndexOutOfBoundsException e) {
            throw new CardAlreadyRemoved("Card already removed at that index");
        }
    }
}
