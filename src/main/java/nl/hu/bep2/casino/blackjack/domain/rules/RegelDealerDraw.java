package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.*;

public class RegelDealerDraw implements PlayPhaseRule {

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer, Modifiers modifiers) {
        if (dealer.score() >= modifiers.getGoalScore()-4)
            dealer.stand();
        if (dealer.score() < modifiers.getGoalScore()-4)
            dealer.draw();
        return PlayerOutcome.CONTINUE;
    }
}
