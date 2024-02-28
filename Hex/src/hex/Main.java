package hex;

import util.*;
import player.*;

public class Main {

	public static void main(String args[]) {

		//Afin de tester les bots, enlever les commentaires ci-dessous et mettre en commentaire ce qui ne l'est pas
		
		Bot B1 = new Bot(1,500);
		Bot B2 = new Bot(2,500);
		Bot B1_2 = new Bot(1,500);
		Bot B2_2 = new Bot(2,500);
		MonteCarloBot mc1 = new MonteCarloBot(1,100);
		MonteCarloBot mc2 = new MonteCarloBot(2,100);

		HumanPlayer P1 = new HumanPlayer(2);
		Game game = new Game(9, mc1, mc2);
		
		
		int winner = 0;
		
		game.displayGrid();
		while(winner < 1) {
			Pair p = game.getCurrentPlayer().play(game);
			winner = game.play(p.getP1(),p.getP2());
			game.displayGrid();
		}
		System.out.println("Le joueur n°"  + game.getWinner().getId() + " a gagné !");
	}

}
