package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfo;
import org.springframework.stereotype.Component;

@Component
public class ModifierValidator {
    public ModifierValidator(){

    }

    public boolean checkModifiers(Modifiers modifiers){
        if (
                decksCheck(modifiers.getDecks()) &&
                        scoreCheck(modifiers.getGoalScore())
        ) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkModifiers(GameInfo gameInfo){
        return checkModifiers(new Modifiers(
                gameInfo.decks,
                gameInfo.goalScore
        ));
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
