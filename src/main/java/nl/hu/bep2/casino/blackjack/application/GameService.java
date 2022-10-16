package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.data.BlackjackGameRepository;
import nl.hu.bep2.casino.blackjack.domain.*;
import nl.hu.bep2.casino.blackjack.domain.exceptions.GameNotFoundException;
import nl.hu.bep2.casino.blackjack.domain.exceptions.InsufficientBalanceException;
import nl.hu.bep2.casino.chips.application.Balance;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameService {
    private final BlackjackGameRepository blackjackGameRepository;
    private final ChipsService chipsService;

    public BlackjackUserView getGameState(long gameId, String username){
        return new BlackjackUserView(getGame(gameId, username));
    }

    public GameService(BlackjackGameRepository blackjackGameRepository, ChipsService chipsService) {
        this.blackjackGameRepository = blackjackGameRepository;
        this.chipsService = chipsService;
    }

    public long startGame(long bet, String username) {
        Balance balance = chipsService.findBalance(username);
        if (balance.getChips() >= bet){
            Game game = new GameFactory().setBet(bet).setDefaultRules().setUsername(username).build();
            chipsService.withdrawChips(username, bet);
            return saveGame(game);
        } else {
            throw new InsufficientBalanceException("User only has " + balance.getChips() + " chips, needs " + bet + " chips.");
        }
    }

    public void hit(long gameId, String username){
        Game game = getGame(gameId, username);
        if (game.getState() != PlayerOutcome.CONTINUE) {return;}

        game.hit();
        saveGame(game);

        payoutIfNeeded(game);
    }

    public void stand(long gameId, String username){
        Game game = getGame(gameId, username);
        if (game.getState() != PlayerOutcome.CONTINUE) {return;}
        game.stand();
        saveGame(game);

        payoutIfNeeded(game);
    }

    public void doubleDown(long gameId, String username){
        Game game = getGame(gameId, username);
        Balance balance = chipsService.findBalance(username);
        if (balance.getChips() >= game.getBet()) {
            chipsService.withdrawChips(username, game.getBet());
            if (game.getState() != PlayerOutcome.CONTINUE) {return;}

            game.doubleDown();
            saveGame(game);

            payoutIfNeeded(game);
        }
    }

    public void endGame(long gameId, String username){
        Game game = getGame(gameId, username);
        if (game.getState() != PlayerOutcome.CONTINUE){
            deleteGame(game);
        }
    }

    public Game getGame(long gameId, String username) {
        Game game = blackjackGameRepository.findGameByIdAndUsername(gameId, username).orElseThrow(() -> new GameNotFoundException("GameId or Username was invalid!"));
        return game;
    }

    public long saveGame(Game game) {
        Long gameid = blackjackGameRepository.save(game).getId();
        return gameid;
    }

    public void deleteGame(Game game) {
        blackjackGameRepository.delete(game);
    }

    //couldn't decide on a good name, basically checks if the game has reached an outcome yet and pays out chips based on that
    private void payoutIfNeeded(Game game) {
        switch (game.getState()){

            case WIN:
                //deposits double their bet
                chipsService.depositChips(game.getUsername(), game.getBet() * 2);
                break;
            case LOSE:
                //Lol you're not getting shit
                break;
            case DRAW:
                //gives them back their bet
                chipsService.depositChips(game.getUsername(), game.getBet());
                break;
            case BLACKJACK:
                chipsService.depositChips(game.getUsername(), (long) (game.getBet()*1.5));
                break;
        }
    }
}
