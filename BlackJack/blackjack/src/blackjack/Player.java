package blackjack;

import cartes.*;
import paquet.*;

/**
 * Classe représentant un joueur de blackjack.
 */
public class Player extends Personne{

	/**
	 * Attribut représentant le nom du joueur.
	 */
	public String name;
	
	/**
	 * Attribut représentant la main du joueur.
	 */
	protected Paquet hand;
	
	/**
	 * Attribut représentant le solde du joueur.
	 */
	protected int money;

	/**
	 * Attribut représentant la mise du joueur.
	 */
	protected int mise;

	/**
	 * Attribut représentant si le joueur a un blackjack.
	 */
	protected boolean isBlackjack;
	
	/**
	 * Constructeur de la classe Player.
	 * @param name Le nom du joueur.
	 * @param money Le solde du joueur.
	 */
	public Player(String name, int money) {
		super();
		super.name = name;
		this.money = money;
		this.mise = 0;
	}

	/**
	 * Methode qui permet d'obtenir la mise du joueur.
	 * @return La mise du joueur.
	 */
	public int getMise() {
		return this.mise;
	}

	/**
	 * Methode qui modifie le solde du joueur.
	 * @param money Le nouveau solde du joueur.
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Methode permettant d'obtenir le solde du joueur.
	 * @return Le solde du joueur.
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Methode permettant de miser de l'argent.
	 * @param mise La mise du joueur.
	 *
	 */
	public void setMise(int mise) {
		this.mise += mise;
		int solde = this.money;
		this.money = solde-mise;
	}
	
	/**
	 * Methode permettant de remettre la mise à 0.
	 */
	public void initMise() {
		this.mise = 0;
	}
	
	/**
	 * Methode permettant d'ajouter les gains au solde du joueur.
	 * @param result Le resultat de la partie.
	 */
	public void setMiseInSoldePlayer(String result) {
		if(result == "win") {
			this.money += (this.mise * 2);			
		} else if(result == "equality") {
			this.money += this.mise;
		}
	}
	
	
	
	
	
}
