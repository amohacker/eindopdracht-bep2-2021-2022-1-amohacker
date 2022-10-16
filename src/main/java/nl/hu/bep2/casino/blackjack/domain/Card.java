package nl.hu.bep2.casino.blackjack.domain;

import org.springframework.transaction.ReactiveTransaction;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {
    Rank rank;
    Suit suit;

    public Card(){}
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
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case ACE:
                return 11;
        }
        return 0;
    }
    public boolean isAce(){
        return rank.equals(Rank.ACE);
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
        JACK,
        QUEEN,
        KING,
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

    public void setRank(Rank rank){
        this.rank = rank;
    }

    public void setSuit(Suit suit){
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
