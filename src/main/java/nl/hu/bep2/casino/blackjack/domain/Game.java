package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.rules.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private Dealer dealer;

    @OneToOne(cascade = CascadeType.ALL)
    private Speler speler;
    @Transient
    private ArrayList<Regel> regels;
    private long bet;
    @Transient
    private Deck deck;
    private PlayerOutcome state;
    private int numberOfDecks = 1;
    private DeckType deckType = DeckType.STANDARD;
    private int doelScore = 21;

    public Game(long bet, ArrayList<Regel> regels, String username, int numberOfDecks, int doelScore, Deck deck){
        this.doelScore = doelScore;
        this.numberOfDecks = numberOfDecks;
        this.deck = deck;
        this.bet = bet;
        this.regels = regels;
        this.speler = new Speler(deck);
        this.dealer = new Dealer(deck);
        this.speler.draw(2);
        this.dealer.draw(1); //will draw another card during turn logic
        this.state = PlayerOutcome.CONTINUE;
        this.speler.setGame(this);
        this.dealer.setGame(this);
        this.username = username;

        turn();
    }

    public Game() {
        regels = new ArrayList<>();
        regels.add(new RegelCheckLose());
        regels.add(new RegelCheckWin());
        regels.add(new RegelDealerAction());
    }

    @PostLoad
    private void unsleep(){
        deck = new DeckFactory().createDeck(getModifiers());
        this.speler.setDeck(deck);
        this.dealer.setDeck(deck);
        deck.removeCards(getDealerCards());
        deck.removeCards(getPlayerCards());
    }


    public void hit(){
        if (state == PlayerOutcome.CONTINUE) {
            speler.draw();
            turn();
        }
    }

    public void stand(){
        if (state == PlayerOutcome.CONTINUE) {
            speler.stand();
            turn();
        }
    }

    public void doubleDown(){
        if (state == PlayerOutcome.CONTINUE) {
            speler.doubleDown();
            bet = bet * 2;
            while (state.equals(PlayerOutcome.CONTINUE)){
                turn();
            }
        }
    }

    private void turn(){
        for (Regel regel: regels) {
            if (regel instanceof PlayPhaseRule)
                regel.check(speler, dealer, getModifiers());
        }
        for (Regel regel: regels) {
            if (regel instanceof WinConditionRule){
                switch(regel.check(speler, dealer, getModifiers())){
                    case WIN:
                        state = PlayerOutcome.WIN;
                        break;
                    case LOSE:
                        state = PlayerOutcome.LOSE;
                        break;
                    case DRAW:
                        state = PlayerOutcome.DRAW;
                }
            }
        }
    }

    public int dealerNumberOfCards(){
        return dealer.getAmountOfCards();
    }

    public Card dealerGetOpenCard(){
        return dealer.getFirstCard();
    }

    @Transient
    public List<Card> getPlayerCards(){

        return speler.getCards();
    }

    public int getPlayerScore(){
        return speler.score();
    }

    public PlayerOutcome getState(){
        return state;
    }

    public long getBet() {
        return bet;
    }

    public String toString() {
        String output = "Dealer: " + dealer.score();
        output += "\nOpen: " + dealer.getFirstCard() + "";
        if (dealer.getCards().size() > 1)
            output += ", Hidden: " + dealer.getCards().subList(1, dealer.getCards().size());
        output += "\n\nPlayer: " + speler.score();
        output += "\nOpen: " + speler.getFirstCard();
        if (speler.getCards().size() > 1)
            output += ", Hidden: " + speler.getCards().subList(1, speler.getCards().size());
        output += "\n";
        return output;
    }

    public List<Card> getDealerCards(){
        return dealer.getCards();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Modifiers getModifiers(){
        return new Modifiers(
                numberOfDecks,
                doelScore
        );
    }
}
