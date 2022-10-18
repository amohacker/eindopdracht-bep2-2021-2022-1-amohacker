package nl.hu.bep2.casino.blackjack.presentation.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Positive;

public class GameInfo {
    @Positive
    public long bet;
    public int decks = 1;
    public int goalScore = 21;

    public String deckType = "standard";
}
