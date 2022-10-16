package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.domain.rules.Regel;
import nl.hu.bep2.casino.blackjack.domain.rules.RegelCheckLose;
import nl.hu.bep2.casino.blackjack.domain.rules.RegelCheckWin;
import nl.hu.bep2.casino.blackjack.domain.rules.RegelDealerDraw;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Regel> regels = new ArrayList<>();
        regels.add(new RegelCheckLose());
        regels.add(new RegelCheckWin());
        regels.add(new RegelDealerDraw());

        Deck deck = new Deck();
        Speler speler = new Speler(deck);
        Game game = new Game(speler, 10, regels, "admin");
        System.out.println(game);
        game.hit();
        System.out.println(game);
        game.hit();
        System.out.println(game);
        game.stand();
        System.out.println(game);
    }
}
