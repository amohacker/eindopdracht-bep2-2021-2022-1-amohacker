package nl.hu.bep2.casino.blackjack.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private ArrayList<Card> drawn = new ArrayList<Card>();
    private Deck deck;

    public Hand (Deck deck){
        this.deck = deck;
    }

    public void draw() {
        drawn.add(deck.draw());
    }

    public int score() {
        int score = 0;
        for (Card card: drawn) {
            score += card.value();
        }
        return collapseAcesIfNeeded(score, numberOfAces());
    }

    public Card getFirstCard() {
        return drawn.get(0);
    }

    public ArrayList<Card> getCards(){
        return drawn;
    }

    public Deck getDeck() {
        return deck;
    }

    private int collapseAcesIfNeeded(int score, int aces){
        if (score > 21 && aces > 0){
            score -= 10;
            aces -= 1;
            return collapseAcesIfNeeded(score, aces);
        }
        return score;
    }

    public int numberOfAces() {
        int num = 0;
        for (Card card: drawn) {
            if (card.isAce())
                num += 1;
        }
        return num;
    }
}
