package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.*;

public class RegelBlackJack implements WinConditionRule {

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer, Modifiers modifiers) {
        if (speler.getAmountOfCards() > 2) {
            return PlayerOutcome.CONTINUE;
        }

        if (speler.score() == modifiers.getGoalScore() && dealer.score() != modifiers.getGoalScore()){
            return PlayerOutcome.BLACKJACK;
        }

        return PlayerOutcome.CONTINUE;
    }
}
