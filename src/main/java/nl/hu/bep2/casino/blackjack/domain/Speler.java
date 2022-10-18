package nl.hu.bep2.casino.blackjack.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.collection.internal.PersistentBag;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Speler implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "speler")
    private Game game;
    @OneToOne(cascade = CascadeType.ALL)
    private Hand hand = new Hand();
    private boolean standing = false;


    public Speler(Deck deck) {
        this.hand = new Hand(deck);
        this.standing = false;
//        handToCards();
    }

    public Speler() {
//        cardsToHand();
    }

    public void draw() {
        hand.draw();
    }

    public void draw(int number) {
        int i = 0;
        while (i<number) {
            hand.draw();
            i++;
        }
    }

    public void stand(){
        standing = true;
    }

    public void doubleDown(){
        draw();
        standing = true;
    }

    public boolean isStanding(){
        return standing;
    }

    public int score(){
//        cardsToHand();
        return hand.score();
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public int getAmountOfCards() {
        return hand.getNumberOfCards();
    }

    public List<Card> getCards(){
        return hand.getCards();
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public void setDeck(Deck deck) {
        this.hand.setDeck(deck);
    }

    @Override
    public String toString() {
        return "Persoon{" +
                "game=" + game.getId() +
                ", hand=" + hand.getCards() +
                ", standing=" + standing +
                '}';
    }
}
