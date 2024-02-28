package player;

import java.util.ArrayList;
import java.util.Scanner;

import hex.Game;
import util.Pair;

/**
 * Classe permettant de représenter un joueur.
 */
public class HumanPlayer extends Player {

	/************************************************************/
	/*********************** CONSTRUCTEUR ***********************/
	/************************************************************/

	/**
	 * @param id : l'identifiant qui sera assigné au joueur
	 */
	public HumanPlayer(int id) {
		this(id, new ArrayList<Integer>());
	}

	/**
	 * @param id : l'identifiant qui sera assigné au joueur
	 * @param pc : la liste des cases du joueur
	 */
	public HumanPlayer(int id, ArrayList<Integer> playerCells) {
		super(id, playerCells);
	}

	/************************************************************/
	/************************* MÉTHODES *************************/
	/************************************************************/

	@Override
	public Pair play(Game game) {
		Pair p = new Pair(0,0);
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("C'est au tour du joueur " + game.getCurrentPlayer() + " !");
			System.out.println("Saisissez la ligne à jouer: ");
			p.setP1(s.nextInt());
			System.out.println("Saisissez la colonne à jouer: ");
			p.setP2(s.nextInt());
		} while(game.isFree(p.getP1(), p.getP2())!=1);
		s.close();
		return p;
	}

}
