package nl.hu.bep2.casino.blackjack.domain;

import org.springframework.transaction.ReactiveTransaction;

public class Card {
    Rank rank;
    Suit suit;
    public Card (Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int value() {
        switch(this.rank){
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case J:
                return 10;
            case Q:
                return 10;
            case K:
                return 10;
            case ACE:
                return 11;
        }
        return 0;
    }
    public boolean isAce(){
        if (rank.equals(Rank.ACE))
            return true;
        return false;
    }

    public enum Rank {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        J,
        Q,
        K,
        ACE
    }
    public enum Suit {
        clubs,
        diamonds,
        hearts,
        spades
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
