package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.rules.*;

import java.util.ArrayList;

public class GameFactory {
    private long bet;
    private ArrayList<Regel> rules;
    private String username;

    private int goalScore = 21;

    private int numberOfDecks = 1;

    public GameFactory setBet(long bet) {
        this.bet = bet;
        return this;
    }

    public GameFactory setDefaultRules() {
        rules = new ArrayList<>();
        rules.add(new RegelCheckLose());
        rules.add(new RegelCheckWin());
        rules.add(new RegelDealerDraw());
        rules.add(new RegelBlackJack());
        return this;
    }

    public GameFactory setGoalScore(int goalScore) {
        this.goalScore = goalScore;
        return this;
    }

    public GameFactory setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        return this;
    }

    public GameFactory setUsername(String username) {
        this.username = username;
        return this;
    }

    public Game build(){
        return new Game(bet, rules, username, numberOfDecks, goalScore);
    }
}
