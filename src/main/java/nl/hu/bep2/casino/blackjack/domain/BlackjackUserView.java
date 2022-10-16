package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackUserView {
    private final Game game;

    public BlackjackUserView(Game game){
        this.game = game;
    }

    public int getDealerHiddenCards(){
        return game.dealerNumberOfCards()-1;
    }

    public List<Card> getDealerCards(){
        if (getGameState() == PlayerOutcome.WIN || getGameState() == PlayerOutcome.LOSE) {
            return game.getDealerCards();
        } else {
            ArrayList<Card> list = new ArrayList<>();
            list.add(game.dealerGetOpenCard());
            return list;
        }
    }

    public List<Card> getPlayerCards(){
        return game.getPlayerCards();
    }

    public int getPlayerScore(){
        return game.getPlayerScore();
    }

    public PlayerOutcome getGameState(){
        return game.getState();
    }
}
