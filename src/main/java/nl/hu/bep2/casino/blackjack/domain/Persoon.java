package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;
import java.util.List;

public abstract class Persoon {
    @Id
    private Long gameId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "gameId")
    private Game game;
    @Transient
    private Hand hand = new Hand();
    @Transient
    private boolean standing = false;

    public Persoon(StandardDeck deck) {
        this.hand = new Hand(deck);
    }

    public Persoon() {
    }

    public void draw() {
        hand.draw();
        standing = false;
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
        return hand.getCards().size();
    }

    @OneToMany
    public List<Card> getCards(){
        return hand.getCards();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setDeck(StandardDeck deck) {
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
