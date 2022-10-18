package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.exceptions.InvalidDeckException;

public class DeckFactory {
    public Deck createDeck(Modifiers modifiers) {
        switch (modifiers.getDeckType()) {
            case STANDARD:
                if (modifiers.getDecks() <= 8) {
                    return new StandardDeckFactory().createDeck(modifiers);
                }
        }

        throw new InvalidDeckException();
    }
}
