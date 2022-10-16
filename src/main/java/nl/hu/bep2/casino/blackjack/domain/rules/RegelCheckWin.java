package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.Dealer;
import nl.hu.bep2.casino.blackjack.domain.PlayerOutcome;
import nl.hu.bep2.casino.blackjack.domain.Persoon;
import nl.hu.bep2.casino.blackjack.domain.Speler;

public class RegelCheckWin implements WinConditionRule {

    @Override
    public PlayerOutcome check(Speler speler, Dealer dealer) {
        if (speler.getAmountOfCards() <= 2) {
            return PlayerOutcome.CONTINUE;
        }
        if (speler.score() == 21 && dealer.isStanding()){
            return PlayerOutcome.WIN;
        }
        if (speler.isStanding() && dealer.isStanding() && speler.score() > dealer.score() && speler.score() <= 21)
            return PlayerOutcome.WIN;
        if (speler.score() <= 21 && dealer.score()>21){
            return PlayerOutcome.WIN;
        }
        return  PlayerOutcome.CONTINUE;
    }
}
