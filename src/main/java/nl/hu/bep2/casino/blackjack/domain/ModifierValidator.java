package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.exceptions.InvalidModifierException;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfo;
import org.springframework.stereotype.Component;

@Component
public class ModifierValidator {
    public ModifierValidator(){

    }

    public boolean checkModifiers(Modifiers modifiers){
        if (
                decksCheck(modifiers.getDecks()) &&
                        scoreCheck(modifiers.getGoalScore()) &&
                        deckTypeCheck(modifiers.getDeckType())

        ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean deckTypeCheck(DeckType deckType) {
        if (deckType != null) {
            return true;
        } else {
            return false;
        }
    }

    public Modifiers checkModifiers(GameInfo gameInfo){
        DeckType deckType = null;
        switch (gameInfo.deckType){
            case "standard":
                deckType = DeckType.STANDARD;
                break;
        }

        Modifiers modifiers = new Modifiers(
                gameInfo.decks,
                gameInfo.goalScore,
                deckType
        );
        if ( checkModifiers(modifiers)) {
          return modifiers;
        } else {
            throw new InvalidModifierException();
        }
    }

    private boolean decksCheck(int decks) {
        if (decks >= 1 && decks <= 8){
            return true;
        } else {
            return false;
        }
    }

    private boolean scoreCheck(int score) {
        if (score >= 21 && score <= 100) {
            return true;
        } else {
            return false;
        }
    }

}
