package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.Dealer;
import nl.hu.bep2.casino.blackjack.domain.PlayerOutcome;
import nl.hu.bep2.casino.blackjack.domain.Persoon;
import nl.hu.bep2.casino.blackjack.domain.Speler;

public class RegelDealerDraw implements PlayPhaseRule {

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer) {
        if (dealer.score() >= 17)
            dealer.stand();
        if (dealer.score() < 17)
            dealer.draw();
        return PlayerOutcome.CONTINUE;
    }
}