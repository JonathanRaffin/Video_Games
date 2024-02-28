package control;

import gui.*;
import hex.*;
import util.*;

public class Execution {

    protected Menu menu;
    protected int taille;
    protected int bot;

	public Execution(Menu menu, int taille, int bot){
        this.menu = menu;
        this.taille = taille;
        this.bot = bot;

        if(this.bot != 0){
            this.executionBot(this.bot);
        } else {
            this.executionPlayer();
        }
    }

    public void executionPlayer(){
        Game game2 = new Game(this.taille);
		System.out.println("Game :");

        Plateau plateau = new Plateau(game2, this.menu, this.bot);
        this.menu.setVisible(false);
    }

    public void executionBot(int bot){

        Game game = new Game(this.taille, bot);
        System.out.println("Game Bot:");

        Plateau plateau = new Plateau(game, this.menu, this.bot);
        this.menu.setVisible(false);
        plateau.play();
        // plateau.update();
        // game.displayGrid();

        // int winner = 0;
        // while(winner < 1) {
        //     Pair p = game.getCurrentPlayer().play(game);
        //     winner = game.play(p.getP1(),p.getP2());
        //     game.displayGrid();
        //     plateau.update();
        // }
        // plateau.update();
    }

}
