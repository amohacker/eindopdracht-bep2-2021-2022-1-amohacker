package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dealer implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "dealer")
    private Game game;
    @OneToOne(cascade = CascadeType.ALL)
    private Hand hand = new Hand();
    private boolean standing = false;

    public Dealer(Deck deck) {
        this.hand = new Hand(deck);
    }

    public Dealer() {

    }

    public void draw() {
        hand.draw();
        standing = false;
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

    public boolean isStanding(){
        return standing;
    }

    public int score(){
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
