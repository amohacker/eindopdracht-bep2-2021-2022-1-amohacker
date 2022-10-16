package nl.hu.bep2.casino.blackjack.domain;

public class Modifiers {
    private int decks;
    private int goalScore;

    public Modifiers(int decks, int goalScore){
        this.decks = decks;
        this.goalScore = goalScore;
    }

    public int getDecks() {
        return decks;
    }

    public int getGoalScore() {
        return goalScore;
    }
}
