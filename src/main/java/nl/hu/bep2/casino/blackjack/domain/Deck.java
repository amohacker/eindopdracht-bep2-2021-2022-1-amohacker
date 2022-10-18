package nl.hu.bep2.casino.blackjack.domain;

import java.util.List;

public interface Deck {
    public Card draw();

    public void removeCards(List<Card> toremove);

    public int getNumberOfCards();
}
