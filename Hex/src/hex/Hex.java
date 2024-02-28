package hex;

import java.util.ArrayList;
import util.*;

/**
 * Classe permettant de représenter la grille du jeu de Hex.
 */
public class Hex {

	/**
	 * Le plateau de jeu.
	 */
	private int[][] grid;

	/**
	 * Liste stockant la valeur des cases.
	 */
	private int[] cellsNumberGrid;

	/**
	 * Stocke les numéros des cases de la ligne du haut (NORD) pour le joueur 1.
	 */
	private ArrayList<Integer> line1Player1 = new ArrayList<Integer>();

	/**
	 * Stocke les numéros des cases de la ligne du bas (SUD) pour le joueur 1.
	 */
	private ArrayList<Integer> line2Player1 = new ArrayList<Integer>();

	/**
	 * Stocke les numéros des cases de la ligne de gauche (OUEST) pour le joueur 2.
	 */
	private ArrayList<Integer> line1Player2 = new ArrayList<Integer>();

	/**
	 * Stocke les numéros des cases de la ligne de droite (EST) pour le joueur 2.
	 */
	private ArrayList<Integer> line2Player2 = new ArrayList<Integer>();

	/************************************************************/
	/*********************** CONSTRUCTEUR ***********************/
	/************************************************************/

	/**
	 * @param nbRow : le nombre de lignes et de colonnes de la grille de jeu
	 * @param P1    : le joueur 1
	 * @param P2    : le joueur 2
	 */
	public Hex(int nbRow) {
		this.grid = new int[nbRow][nbRow];
		this.cellsNumberGrid = new int[nbRow * nbRow];
		createGrid(nbRow);
	}

	/**
	 * @param x : Jeu de hex qui va être copié
	 */
	public Hex(Hex x) {
		this.grid = new int[x.getGridSize()][x.getGridSize()];
		this.cellsNumberGrid = new int[x.getGridSize()*x.getGridSize()];
		createGridFromHex(x.getGridSize(),x);
	}

	/************************************************************/
	/************************** GETTERS *************************/
	/************************************************************/

	/**
	 * Permet de connaitre la valeur d'une case donnée.
	 * 
	 * @param x : le numéro de la ligne
	 * @param y : le numéro de la colonne
	 * @return la valeur de la case
	 */
	public int getCellValue(int x, int y) {
		return this.grid[x][y];
	}

	/**
	 * Permet de récupérer la liste stockant la valeur des cases.
	 * 
	 * @return la liste stockant la valeur des cases
	 */
	public int[] getCellsNumberGrid() {
		return cellsNumberGrid;
	}

	/**
	 * Permet de connaitre les numéros des cases de la ligne du haut (NORD) pour le joueur 1.
	 * 
	 * @return la liste des numéros des cases de la ligne du haut (NORD) pour le joueur 1
	 */
	public ArrayList<Integer> getLine1Player1() {
		return line1Player1;
	}

	/**
	 * Permet de connaitre les numéros des cases de la ligne du bas (SUD) pour le joueur 1.
	 * 
	 * @return la liste des numéros des cases de la ligne du bas (SUD) pour le joueur 1
	 */
	public ArrayList<Integer> getLine2Player1() {
		return line2Player1;
	}

	/**
	 * Permet de connaitre les numéros des cases de la ligne de gauche (OUEST) pour le joueur 2.
	 * 
	 * @return la liste des numéros des cases de la ligne de gauche (OUEST) pour le joueur 2
	 */
	public ArrayList<Integer> getLine1Player2() {
		return line1Player2;
	}

	/**
	 * Permet de connaitre les numéros des cases de la ligne de droite (EST) pour le joueur 2.
	 * 
	 * @return la liste des numéros des cases de la ligne de droite (EST) pour le joueur 2
	 */
	public ArrayList<Integer> getLine2Player2() {
		return line2Player2;
	}

	/**
	 * Permet de récupérer la grille du jeu.
	 * 
	 * @return la grille du jeu
	 */
	public int[][] getGrid() {
		return this.grid;
	}

	/**
	 * Permet de récupérer la taille de la grille du jeu.
	 * 
	 * @return la taille de la grille du jeu
	 */
	public int getGridSize() {
		return this.grid.length;
	}

	/************************************************************/
	/************************** SETTERS *************************/
	/************************************************************/

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	/************************************************************/
	/************************* MÉTHODES *************************/
	/************************************************************/

	/**
	 * Permet de convertir le numéro d'une case en ses coordonnées x et y.
	 * 
	 * @param c : le numéro de la case
	 * @return un tableau contenant le numéro de la ligne et le numéro de la colonne
	 */
	public int[] cellNumberToCoord(int c) {
		int[] t = new int[2];
		int x = c / this.grid.length;
		int y = c % this.grid.length;
		t[0] = x;
		t[1] = y;
		return t;
	}

	/**
	 * Permet de convertir les coordonnées x et y d'une case en son numéro.
	 * 
	 * @param x : le numéro de la ligne
	 * @param y : le numéro de la colonne
	 * @return le numéro de la case
	 */
	public int coordToCellNumber(int x, int y) {
		return x * this.grid.length + y;
	}

	/**
	 * Permet de créer la grille du jeu.
	 * 
	 * @param nbRow : le nombre de lignes et de colonnes de la grille de jeu
	 */
	public void createGrid(int nbRow) {
		this.createGridFromHex(nbRow, null);
	}
	
	/**
	 * Permet de créer la grille du jeu à partir d'une autre grille.
	 * 
	 * @param nbRow : le nombre de lignes et de colonnes de la grille de jeu
	 * @param x : Jeu de Hex qui va permettre la copie
	 */
	private void createGridFromHex(int nbRow, Hex x) {
		int i;
		int j;
		for (i = 0; i < nbRow; i++) {
			for (j = 0; j < nbRow; j++) {
				// Initialise la grille avec les coordonées des cases
				this.grid[i][j] = x != null ? x.getGrid()[i][j] : 0;
				// Initialise la grille avec la valeur des cases
				this.cellsNumberGrid[i * j] = coordToCellNumber(i, j);
			}
		}

		// Ligne du haut
		for (i = 0; i < 1; i++)
			for (j = 0; j < nbRow; j++)
				this.line1Player1.add(this.coordToCellNumber(i, j));
		// Ligne du bas
		for (i = nbRow - 1; i < nbRow; i++)
			for (j = 0; j < nbRow; j++)
				this.line2Player1.add(this.coordToCellNumber(i, j));
		// Ligne de gauche
		for (i = 0; i < nbRow; i++)
			for (j = 0; j < 1; j++)
				this.line1Player2.add(this.coordToCellNumber(i, j));
		// Ligne de droite
		for (i = 0; i < nbRow; i++)
			for (j = nbRow - 1; j < nbRow; j++)
				this.line2Player2.add(this.coordToCellNumber(i, j));
	}

	/**
	 * Permet de connaitre les numéros des cases voisines à une case donnée.
	 * 
	 * @param x : le numéro de la ligne de la case dont on veut connaitre les
	 *          voisins
	 * @param y : le numéro de la colonne de la case dont on veut connaitre les
	 *          voisins
	 * @return un tableau d'entier contenant les numéros des cases voisines
	 */
	public ArrayList<Integer> getVoisin(int x, int y) {
		ArrayList<Integer> voisin = new ArrayList<Integer>();
		if (y != 0)
			voisin.add(x * this.grid.length + (y - 1));
		if (y != this.grid.length - 1)
			voisin.add(x * this.grid.length + (y + 1));
		if (y != this.grid.length - 1 && x != 0)
			voisin.add((x - 1) * this.grid.length + (y + 1));
		if (y != 0 && x != this.grid.length - 1)
			voisin.add((x + 1) * this.grid.length + (y - 1));
		if (x != 0)
			voisin.add((x - 1) * this.grid.length + y);
		if (x != this.grid.length - 1)
			voisin.add((x + 1) * this.grid.length + y);
		return voisin;
	}
	
	/**
	 * Permet de récupérer toutes les cases correspondantes à une valeur.
	 * 
	 * @param value : la valeur de la case
	 * @return une arrayList de paire
	 */
	public ArrayList<Pair> getCaseOfValue(int value){
		ArrayList<Pair> toReturn = new ArrayList<Pair>();
		for(int i = 0; i<this.getGridSize();i++)
			for(int j = 0; j<this.getGridSize();j++)
				if(this.grid[i][j]==value)
					toReturn.add(new Pair(i,j));
		return toReturn;	
	}

}