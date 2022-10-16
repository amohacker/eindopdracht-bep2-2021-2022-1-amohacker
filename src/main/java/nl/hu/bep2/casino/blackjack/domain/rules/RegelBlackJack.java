package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.Dealer;
import nl.hu.bep2.casino.blackjack.domain.PlayerOutcome;
import nl.hu.bep2.casino.blackjack.domain.Speler;

public class RegelBlackJack implements WinConditionRule {

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer) {
        if (speler.getAmountOfCards() > 2) {
            return PlayerOutcome.CONTINUE;
        }

        if (speler.score() == 21 && dealer.score() != 21){
            return PlayerOutcome.BLACKJACK;
        }

        return PlayerOutcome.CONTINUE;
    }
}
