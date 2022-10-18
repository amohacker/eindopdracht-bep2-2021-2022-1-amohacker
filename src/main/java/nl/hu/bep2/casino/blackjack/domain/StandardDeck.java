package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class StandardDeck implements Deck{
    private ArrayList<Card> cards;

    public StandardDeck(int numberOfDecks){
        cards = new ArrayList<Card>();
//        int cardsgenerated = 0;  //debugging tool
            for (Card.Rank rank : Card.Rank.values()) {
                for (Card.Suit suit : Card.Suit.values()) {
                    for (int i = 0; i < numberOfDecks; i++) {
                        cards.add(new Card(rank, suit));
//                        cardsgenerated++;
                    }
                }
            }
//        System.out.println("Cardsgenerated: " + cardsgenerated);
//        System.out.println(cards);
    }

    public Card draw() {
        //Generate random integer between 0 and the highest index of cards
        int randomNum = (int) (Math.random() * (cards.size()-1));
        Card drawn = cards.get(randomNum);
        //Removes drawn card from deck and then returns it
        cards.remove(drawn);
        return drawn;
    }

    public void removeCards(List<Card> toremove){
        for (Card card : toremove) {
            cards.remove(card);
        }
    }

    public int getNumberOfCards() {
        return cards.size();
    }
}
