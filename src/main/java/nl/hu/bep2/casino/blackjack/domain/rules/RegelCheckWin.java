package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.*;

public class RegelCheckWin implements WinConditionRule {

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer, Modifiers modifiers) {
        if (speler.score() == modifiers.getGoalScore() && speler.getAmountOfCards() == 2) {
            return PlayerOutcome.CONTINUE;
        }
        if (speler.score() == modifiers.getGoalScore() && dealer.isStanding()){
            return PlayerOutcome.WIN;
        }
        if (speler.isStanding() && dealer.isStanding() && speler.score() > dealer.score() && speler.score() <= modifiers.getGoalScore())
            return PlayerOutcome.WIN;
        if (speler.score() <= modifiers.getGoalScore() && dealer.score()>modifiers.getGoalScore()){
            return PlayerOutcome.WIN;
        }
        return  PlayerOutcome.CONTINUE;
    }
}
