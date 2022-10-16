package nl.hu.bep2.casino.blackjack.presentation.controller;

import nl.hu.bep2.casino.blackjack.application.GameService;
import nl.hu.bep2.casino.blackjack.domain.BlackjackUserView;
import nl.hu.bep2.casino.blackjack.presentation.dto.Bet;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameID;
import nl.hu.bep2.casino.chips.presentation.dto.Deposit;
import nl.hu.bep2.casino.security.domain.UserProfile;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/blackjack")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @PostMapping("/start")
    public long startGame(Authentication authentication, @Validated @RequestBody Bet bet) {
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            if (profile.getUsername() == null){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        try {
            return gameService.startGame(bet.bet, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/state")
    public BlackjackUserView getGameState(Authentication authentication, @Validated @RequestBody GameID gameID) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        if (profile.getUsername() == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        try {
            return gameService.getGameState(gameID.gameid, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/hit")
    public BlackjackUserView hit(Authentication authentication, @Validated @RequestBody GameID gameId) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        if (profile.getUsername() == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        try {
            gameService.hit(gameId.gameid, profile.getUsername());
            return gameService.getGameState(gameId.gameid, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/stand")
    public BlackjackUserView stand(Authentication authentication, @Validated @RequestBody GameID gameId) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();

        if (profile.getUsername() == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        try {
            gameService.stand(gameId.gameid, profile.getUsername());
            return gameService.getGameState(gameId.gameid, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/double")
    public BlackjackUserView doubleDown(Authentication authentication, @Validated @RequestBody GameID gameId) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();
        if (profile.getUsername() == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        try {
            gameService.doubleDown(gameId.gameid, profile.getUsername());
            return gameService.getGameState(gameId.gameid, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/end")
    public void endGame(Authentication authentication, @Validated @RequestBody GameID gameId) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();

        if (profile.getUsername() == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        try {
            gameService.endGame(gameId.gameid, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}