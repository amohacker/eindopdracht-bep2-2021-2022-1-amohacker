package nl.hu.bep2.casino.blackjack.domain;

public class Persoon {
    private Hand hand;

    public Persoon(Deck deck) {
        this.hand = new Hand(deck);
    }

    public void draw() {
        hand.draw();
    }

    public void stand(){

    }

    public int score(){
        return hand.score();
    }

    public Card getFirstCard() {
        return hand.getFirstCard();
    }

    public Deck getDeck() {
        return hand.getDeck();
    }}
