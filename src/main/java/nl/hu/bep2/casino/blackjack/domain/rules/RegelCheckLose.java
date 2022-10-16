package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.Dealer;
import nl.hu.bep2.casino.blackjack.domain.PlayerOutcome;
import nl.hu.bep2.casino.blackjack.domain.Persoon;
import nl.hu.bep2.casino.blackjack.domain.Speler;

public class RegelCheckLose implements WinConditionRule {
    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer) {
        if (speler.score() > 21 && dealer.score() <= 21)
            return PlayerOutcome.LOSE;
        if (speler.isStanding() && dealer.isStanding() &&
                dealer.score() > speler.score() && dealer.score() <= 21)
            return PlayerOutcome.LOSE;
        return PlayerOutcome.CONTINUE;
    }
}
