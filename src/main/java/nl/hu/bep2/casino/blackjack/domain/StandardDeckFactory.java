package nl.hu.bep2.casino.blackjack.domain;

public class StandardDeckFactory extends DeckFactory {
    @Override
    public Deck createDeck(Modifiers modifiers) {
        return new StandardDeck(modifiers.getDecks());
    }
}
