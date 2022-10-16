package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.Dealer;
import nl.hu.bep2.casino.blackjack.domain.PlayerOutcome;
import nl.hu.bep2.casino.blackjack.domain.Persoon;
import nl.hu.bep2.casino.blackjack.domain.Speler;

public interface Regel {
    PlayerOutcome check(Speler speler, Dealer dealer);
}
