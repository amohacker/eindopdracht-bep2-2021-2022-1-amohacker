package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.*;

public class RegelDraw implements WinConditionRule{

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer, Modifiers modifiers) {
        if (speler.score()>modifiers.getGoalScore() && dealer.score()>modifiers.getGoalScore()){
            return PlayerOutcome.DRAW;
        }
        if (speler.isStanding() && dealer.isStanding() && dealer.score() == speler.score()) {
            return PlayerOutcome.DRAW;
        }


        return PlayerOutcome.CONTINUE;
    }
}
