package nl.hu.bep2.casino.blackjack.domain;

public class Modifiers {
    private int decks;
    private int goalScore;
    private DeckType deckType = DeckType.STANDARD;

    public Modifiers(int decks, int goalScore){
        this.decks = decks;
        this.goalScore = goalScore;
    }

    public Modifiers(int decks, int goalScore, DeckType deckType){
        this(decks, goalScore);
        this.deckType = deckType;
    }

    public int getDecks() {
        return decks;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public DeckType getDeckType() {
        return deckType;
    }
}
