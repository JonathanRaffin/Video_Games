package blackjack;

import cartes.Carte;
import paquet.Paquet;

/**
 * Classe représentant une personne.
 */
public abstract class Personne {
	
	/**
	 * Attribut représentant le nom de la personne.
	 */
	public String name;

	/**
	 * Attribut représentant la main de la personne.
	 */
	protected Paquet hand;

	/**
	 * Attribut booleen représentant si la personne a un blackjack.
	 */
	protected boolean isBlackjack;

	/**
	 * Attribut booleen représentant si la personne a gagné.
	 */
	protected boolean hasWin;
	
	/**
	 * Constructeur de la classe Personne.
	 */
	public Personne() {
		this.name = "";
		this.hand = new Paquet();
		this.isBlackjack=false;
		this.hasWin=false;
	}

	/**
	 * Methode qui renvoie si la personne a un blackjack.
	 * @return la personne a un blackjack.
	 */
	public boolean isBlackjack() {
		return isBlackjack;
	}
	
	/**
	 * Methode qui permet à la personne de choisir s'il veut changer.
	 */
	public void changer() {
		this.isBlackjack = this.isBlackjack == true ? false : true;
	}
	
	/**
	 * Methode qui permet de savoir si le joueur a gagné.
	 * @return la personne a gagné.
	 */
	public boolean isHasWin() {
		return hasWin;
	}

	/**
	 * Methode qui permet de modifier l'attribut hasWin.
	 * Si la personne a gagné, hasWin est à true sinon à false.
	 */
	public void changeHasWin() { //
		this.hasWin = this.hasWin == true ? false : true;
	}

	/**
	 * Methode qui permet d'obtenir le nom du joueur.
	 * @return le nom de la personne.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Methode qui permet d'obtenir la main du joueur.
	 * @return la main du joueur.
	 */
	public Paquet getHand() {
		return hand;
	}
	
	/**
	 * Methode qui permet d'initialiser la main du joueur.
	 */
	public void initHand() {
        this.hand= new Paquet();
	}
	
	/**
	 * Methode qui permet d'ajouter une carte à la main du joueur.
	 * @param carte La carte à ajouter.
	 */
	public void CardToHand(Carte c) {
		this.hand.ajoutFin(c);
	}
	
	/**
	 * Methode qui représente la reinitialisation.
	 */
	public void reinit() {
		this.isBlackjack = false;
		this.hasWin = false;
	}
}
