package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hand implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    private List<Card> drawn = new ArrayList<>();
    @Transient
    private Deck deck;

    public Hand (Deck deck){
        this.deck = deck;
    }

    public Hand(){
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

    public List<Card> getCards(){
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for (Card card : drawn) {
            cardArrayList.add(card);
        }
        return cardArrayList;
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

    public void setCards(ArrayList<Card> cards){
        this.drawn = cards;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
