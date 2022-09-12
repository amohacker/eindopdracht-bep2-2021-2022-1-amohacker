package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;

public class Game {
    private Persoon dealer;
    private Persoon speler;
    private ArrayList<Regel> regels;
    private double bet;
    private Deck deck;

    public Game(Persoon speler, double bet, ArrayList<Regel> regels) {
        this.speler = speler;
        speler.getDeck();
        this.bet = bet;
        this.regels = regels;
        this.dealer = new Persoon(deck);
    }
}
