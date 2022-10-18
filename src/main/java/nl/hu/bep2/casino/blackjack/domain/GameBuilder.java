package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.rules.*;

import java.util.ArrayList;

public class GameBuilder {
    private long bet;
    private ArrayList<Regel> rules;
    private String username;
    private int goalScore = 21;
    private int numberOfDecks = 1;

    private DeckType deckType = DeckType.STANDARD;

    public GameBuilder setBet(long bet) {
        this.bet = bet;
        return this;
    }

    public GameBuilder setDefaultRules() {
        rules = new ArrayList<>();
        rules.add(new RegelCheckLose());
        rules.add(new RegelCheckWin());
        rules.add(new RegelDealerAction());
        rules.add(new RegelBlackJack());
        rules.add(new RegelDraw());
        return this;
    }

    public GameBuilder setGoalScore(int goalScore) {
        this.goalScore = goalScore;
        return this;
    }

    public GameBuilder setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        return this;
    }

    public GameBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public GameBuilder setDeckType(DeckType deckType){
        this.deckType = deckType;
        return this;
    }

    public Game build(){
        Deck deck = new DeckFactory().createDeck(new Modifiers(numberOfDecks, goalScore, deckType));
        return new Game(bet, rules, username, numberOfDecks, goalScore, deck);
    }
}
