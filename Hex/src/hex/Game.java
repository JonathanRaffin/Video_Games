package hex;

import java.util.ArrayList;
import java.util.Scanner;

import player.*;

public class Game {

    /**
     * Hex
     */
    Hex hex;

    /**
     * Taille du plateau de jeu.
     */
    int size;

    /**
	 * Indique le nombre de tours qui sont passés.
	 */
    int turn;

    /**
	 * Joueur
	 */
    Player player1, player2, currentPlayer;

	/************************************************************/
	/*********************** CONSTRUCTEUR ***********************/
	/************************************************************/
	
	/**
	 * @param size : la taille du jeu
	 * @param P1 : le joueur 1
	 * @param P2 : le joueur 2
	 * @param hex : le Hex à copier
	 */
    public Game (int size, Player P1, Player P2, Hex hex) {
        this.size = size;
        this.hex = hex == null ? new Hex(this.size) : new Hex(hex);
        this.turn = 0;
		this.player1 = P1;
		this.player2 = P2;
		this.currentPlayer = this.player1;
    }

	/**
	 * @param size : la taille du jeu
	 * @param P1 : le joueur 1
	 * @param P2 : le joueur 2
	 */
    public Game (int size, Player P1, Player P2) {
        this(size, P1, P2, null);
    }

	/**
	 * @param size : la taille du jeu
	 */
    public Game (int size) {
        this(size, 0);
    }

	/**
	 * @param size : la taille du jeu
	 * @param bot : un booléen indiquant si on utilise des bots, ou non
	 */
    public Game (int size, int bot) {
		if (bot == 1) {
			Bot B1 = new Bot(1,5000);
			Bot B2 = new Bot(2,5000);
			Bot B1_2 = new Bot(1,2000);
			Bot B2_2 = new Bot(2,2000);
			this.player1 = B1_2;
			this.player2 = B2_2;
		} else if (bot == 2) {
			MonteCarloBot mc1 = new MonteCarloBot(1,500);
			MonteCarloBot mc2 = new MonteCarloBot(2,500);
			this.player1 = mc1;
			this.player2 = mc2;
		}
		else {
			this.player1 = new Player(1);
			this.player2 = new Player(2);
		}
		this.size = size;
        this.hex = new Hex(this.size);
        this.turn = 0;
		this.currentPlayer = this.player1;
    }

	/**
	 * @param game : le Game à copier
	 */
    public Game (Game game) {
        this(game.getSize(), new Player(game.getPlayer1().getId(), (ArrayList<Integer>) game.getPlayer1().getPlayerCells().clone()), new Player(game.getPlayer2().getId(), (ArrayList<Integer>) game.getPlayer2().getPlayerCells().clone()), game.getHex());
    }

	/************************************************************/
	/************************** GETTERS *************************/
	/************************************************************/

	/**
	 * Permet de récupérer la taille du plateau de jeu.
	 * 
	 * @return la taille du jeu
	 */
    public int getSize() {
        return this.size;
    }

	/**
	 * Permet de récupérer le Hex.
	 * 
	 * @return le Hex pris en argument par le Game
	 */
    public Hex getHex() {
        return this.hex;
    }

    /**
	 * Permet de récupérer la grille du jeu.
	 * 
	 * @return la grille du jeu
	 */
	public int[][] getGrid() {
		return this.hex.getGrid();
	}

    /**
	 * Permet de récupérer le joueur 1.
	 * 
	 * @return le joueur 1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Permet de récupérer le joueur 2.
	 * 
	 * @return le joueur 2
	 */
	public Player getPlayer2() {
		return player2;
	}

    /**
	 * Permet de connaitre le joueur actuel.
	 * 
	 * @return le joueur actuel
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

    /************************************************************/
	/************************** SETTERS *************************/
	/************************************************************/

    /**
	 * Permet de passer au joueur suivant (par exemple : si le joueur actuel est le
	 * joueur 1, alors, après appel de cette fonction le joueur actuel sera le
	 * joueur 2).
	 */
	public void nextPlayer() {
		this.turn += 1;
		this.currentPlayer = (this.currentPlayer == this.player1 ? this.player2 : this.player1);
	}

	/************************************************************/
	/************************* MÉTHODES *************************/
	/************************************************************/

    /**
	 * Permet de déterminer si la partie est finie, et si tel est le cas, quel joueur a gagné la partie.
	 * 
	 * @return null si la partie n'est pas terminée, ou le joueur gagnant lorsque la partie est finie
	 */
	public Player getWinner() {
		//On cherche le chemin complet pour chaque case du joueur actuel
		for (int s : this.currentPlayer.getPlayerCells()) {
			ArrayList<Integer> chemin = this.getCaseParcouru(this.hex.cellNumberToCoord(s)[0], this.hex.cellNumberToCoord(s)[1], new ArrayList<Integer>(), new ArrayList<Integer>());
			//Vérification de la victoire du joueur1 en comparant les cases en extrémités et le chemin du joueur
			for (int x : this.hex.getLine1Player1())
				if (chemin.contains(x))
					for (int w : this.hex.getLine2Player1())
						if (chemin.contains(w) && this.currentPlayer.getId() == 1)
							return this.player1;
			//Vérification de la victoire du joueur2 en comparant les cases en extrémités et le chemin du joueur
			for (int x : this.hex.getLine1Player2())
				if (chemin.contains(x))
					for (int w : this.hex.getLine2Player2())
						if (chemin.contains(w) && this.currentPlayer.getId() == 2)
							return this.player2;
		}
		return null;
	}

    /**
	 * Permet de récupérer toutes les cases parcourues par un joueur sous forme d'un chemin.
	 * 
	 * @param x      : le numéro de la ligne
	 * @param y      : le numéro de la colonne
	 * @param closed : arrayList contenant les cases déjà explorées
	 * @param chemin : arrayList contenant les cases du chemin
	 * @return arrayList contenant toutes les cases parcourues par un joueur dans l'ordre de parcours
	 */
	public ArrayList<Integer> getCaseParcouru(int x, int y, ArrayList<Integer> closed, ArrayList<Integer> chemin) {
		// Marquer le sommet
		closed.add(this.hex.coordToCellNumber(x, y));
		chemin.add(this.hex.coordToCellNumber(x, y));
		for (int v : this.hex.getVoisin(x, y))
			if (this.currentPlayer.getPlayerCells().contains(v))
				if (!closed.contains(v))
					this.getCaseParcouru(this.hex.cellNumberToCoord(v)[0], this.hex.cellNumberToCoord(v)[1], closed, chemin);
		return chemin;
	}

    /**
	 * Permet au joueur actuel de jouer un coup sur une case si elle est disponible.
	 * 
	 * @param x : le numéro de la ligne
	 * @param y : le numéro de la colonne
	 */
	public void place(int x, int y) {
		if (this.isFree(x, y) == 1)
			this.select(x, y);
		else if (this.isFree(x, y) == 2)
			this.swap(x, y);
	}

	/**
	 * Permet au joueur actuel de sélectionner une case.
	 * 
	 * @param x : le numéro de la ligne
	 * @param y : le numéro de la colonne
	 */
	public void select(int x, int y) {
		int[][] grid = this.getGrid();
		grid[x][y] = this.currentPlayer.getId();
		this.hex.setGrid(grid);
		this.currentPlayer.addPlayerCell(this.hex.coordToCellNumber(x, y));
	}

	/**
	 * Permet au joueur actuel de jouer un swap.
	 * 
	 * @param x : le numéro de la ligne
	 * @param y : le numéro de la colonne
	 */
	public void swap(int x, int y) {
		int[][] grid = this.getGrid();
		grid[x][y] = 0;
		this.hex.setGrid(grid);
		if (this.currentPlayer.getId() == this.player1.getId())
			this.player2.resetPlayerCells();
		else
			this.player1.resetPlayerCells();
		this.select(y, x);
	}

	/**
	 * Permet de déterminer si une case est disponible ou non.
	 * 
	 * @param x : le numéro de la ligne
	 * @param y : le numéro de la colonne
	 * @return 1 si la case est libre, 2 si la case est occupée mais qu'on peut jouer un swap (car on est au premier tour),
	 * -2 si la case est occupée, -1 si les coordonnées sont incorrectes
	 */
	public int isFree(int x, int y) {
		int[][] grid = this.getGrid();
		if (x <= grid.length && y <= grid.length && x >= 0 && y >= 0) { // Vérifie que les coordonnées sont valides
			if (grid[x][y] == 0) // Vérifie que la case n'est pas déjà occupée par l'autre joueur
				return 1;
			else
				return this.turn == 1 ? 2 : -2; // si on est au 1er tour on peut jouer un swap (donc return 2) sinon on ne peut pas jouer (return -2)
		} else {
			return -1;
		}
	}

	/**
	 * Permet de jouer au jeu
	 * 
	 * @param row : le numéro de la ligne de la case selectionnée
	 * @param column : le numéro de la colonne de la case selectionnée
	 * @return l'identifiant du gagnant de la partie (donc 1 ou 2), ou 0 si la partie n'est pas encore terminée
	 */
	public int play(int row, int column) {
		Player winner = null;
		int isFree = isFree(row, column);
		if(isFree == 1 || isFree == 2) {
			this.place(row, column);
			winner = this.getWinner();
			if (winner != null) {
				return winner.getId();
			}
			else {
				this.nextPlayer();
				return 0;
			}
		}
		else {
			return isFree;
		}
	}

	/**
	 * Permet à un utilisateur (humain) de jouer au jeu dans un terminal
	 */
	public void playInTerminal() {
		int winner = 0;
		Scanner s = new Scanner(System.in);
		
		this.displayGrid();
		
		while(winner < 1) {
			System.out.println("C'est au tour du joueur " + this.currentPlayer + " !");
			System.out.println("Saisissez la ligne à jouer: ");
			int row = s.nextInt();
			System.out.println("Saisissez la colonne à jouer: ");
			int column = s.nextInt();	

			winner = this.play(row-1, column-1);
			
			this.displayGrid();
		}
		s.close();
		System.out.println("Le joueur " + winner + " a gagné !");
	}

	/************************************************************/
	/************************* AFFICHAGE ************************/
	/************************************************************/

	/**
	 * Permet un affichage "propre" de la grille du jeu en console.
	 */
	public void displayGrid() {
		int[][] grid = this.getGrid();
        String v = "";
        System.out.print("  | ");
        for (int i = 1; i <= grid.length; i++) {
                if (i < 10) System.out.print(i + " | "); 
                else System.out.print(i + "| "); // Pour la transition du chiffre 9 au nombre 10 dans l'affichage
        }
        System.out.println("");
        for(int i = 0; i < grid.length; i++) {        
                System.out.print("" + v + (i+1));
                if (i == 8) v += " "; // Pour la transition du chiffre 9 au nombre 10 dans l'affichage
                else v += "  ";
                for(int j = 0; j < grid.length; j++) {
                        System.out.print(" | " + grid[i][j]);
                }
                System.out.println(" | ");
        }
        System.out.println("");
	}
    
}