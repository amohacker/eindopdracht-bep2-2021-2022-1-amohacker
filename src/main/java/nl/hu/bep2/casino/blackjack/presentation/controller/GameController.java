package nl.hu.bep2.casino.blackjack.presentation.controller;

import nl.hu.bep2.casino.blackjack.application.GameService;
import nl.hu.bep2.casino.blackjack.domain.BlackjackUserView;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameID;
import nl.hu.bep2.casino.blackjack.presentation.dto.GameInfo;
import nl.hu.bep2.casino.security.domain.UserProfile;
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
    public long startGame(Authentication authentication, @Validated @RequestBody GameInfo gameInfo) {
            UserProfile profile = (UserProfile) authentication.getPrincipal();
            if (profile.getUsername() == null){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        try {
            return gameService.startGame(gameInfo, profile.getUsername());
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

    @PostMapping("/surrender")
    public void surrender(Authentication authentication, @Validated @RequestBody GameID gameId) {
        UserProfile profile = (UserProfile) authentication.getPrincipal();

        if (profile.getUsername() == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        try {
            gameService.surrender(gameId.gameid, profile.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public void deleteGame(Authentication authentication, @Validated @RequestBody GameID gameId) {
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