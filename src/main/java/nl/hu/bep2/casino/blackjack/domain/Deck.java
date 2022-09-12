package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();
        for (Card.Rank rank : Card.Rank.values()){
            for (Card.Suit suit : Card.Suit.values()){
                cards.add(new Card(rank, suit));
            }
        }
    }

    public Card draw() {
        //Generate random integer between 0 and the highest index of cards
        int randomNum = (int) (Math.random() * (cards.size()-1));
        Card drawn = cards.get(randomNum);
        //Removes drawn card from deck and then returns it
        cards.remove(drawn);
        return drawn;
    }
}
