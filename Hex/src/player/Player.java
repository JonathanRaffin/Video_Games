package player;

import java.util.ArrayList;

import util.*;
import hex.Game;

public class Player {

    /**
	 * Identifiant du joueur (sous la forme d'un entier)
	 */
	protected int id;

	/**
	 * Liste des hexagones occupés par le joueur
	 */
	protected ArrayList<Integer> playerCells;

	/************************************************************/
	/*********************** CONSTRUCTEUR ***********************/
	/************************************************************/

    /**
	 * @param id : l'identifiant qui sera assigné au joueur
	 */
	public Player(int id) {
		this(id, new ArrayList<Integer>());
	}

	/**
	 * @param id : l'identifiant qui sera assigné au joueur
	 * @param pc : la liste des cases du joueur
	 */
	public Player(int id, ArrayList<Integer> playerCells) {
		this.id = id;
		this.playerCells = playerCells;
	}

    /************************************************************/
	/************************** GETTERS *************************/
	/************************************************************/

	/**
	 * Permet de récupérer l'identifiant du joueur.
	 * 
	 * @return l'identifiant du joueur
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Permet de récupérer la liste des hexagones occupés par le joueur
	 * 
	 * @return la liste des hexagones occupés par le joueur
	 */
	public ArrayList<Integer> getPlayerCells() {
		return this.playerCells;
	}

	/************************************************************/
	/************************** SETTERS *************************/
	/************************************************************/

	/**
	 * Ajoute le numéro de l'hexagone donné en paramètre à la liste des hexagones occupés par le joueur
	 * 
	 * @param r : le numéro de l'hexagone
	 */
	public void addPlayerCell(int r) {
		this.playerCells.add(r);
	}

    /************************************************************/
	/************************* MÉTHODES *************************/
	/************************************************************/

	/**
	 * Réinitialise la liste des hexagones occupés par le joueur
	 */
	public void resetPlayerCells() {
		this.playerCells.clear();
	}

	/**
	 * Permet d'isoler le tour du joueur en une fonction (pour permettre de le faire jouer contre un bot)
	 * 
	 * @param h : le jeu de hex actuel
	 * @return la paire qui sera jouée par le joueur
	 */
	 public Pair play(Game game) {
        throw new Error("Méthode non implémentée pour le type Player.");
     }

	/************************************************************/
	/************************* TO STRING ************************/
	/************************************************************/

	@Override
	public String toString() {
		return "" + this.id;
	}

}
