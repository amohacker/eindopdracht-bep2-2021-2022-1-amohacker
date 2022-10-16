package nl.hu.bep2.casino.blackjack.domain.rules;

import nl.hu.bep2.casino.blackjack.domain.*;

public interface Regel {
    PlayerOutcome check(Speler speler, Dealer dealer, Modifiers modifiers);
}
