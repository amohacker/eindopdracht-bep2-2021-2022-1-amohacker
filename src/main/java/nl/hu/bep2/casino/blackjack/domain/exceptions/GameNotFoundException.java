package nl.hu.bep2.casino.blackjack.domain.exceptions;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException(String message){
        super(message);
    }
}
