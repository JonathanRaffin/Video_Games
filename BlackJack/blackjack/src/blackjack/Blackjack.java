package blackjack;

import java.util.*;

import cartes.*;
import paquet.*;

/**
 * Classe représentant le jeu Blackjack.
 */
public class Blackjack {
	
	/**
	 * Attribut représentant la pioche du Paquet.
	 */
	protected Paquet pioche;

	/**
	 * Attribut représentant le croupier.
	 */
	protected Croupier croupier;

	/**
	 * Attribut représentant le joueur.
	 */
	protected Player player1;

	/**
	 * Constructeur de la classe Blackjack.
	 */
	public Blackjack() {
		this.pioche= Factory.pioche();
		this.pioche.melanger();
		this.croupier = new Croupier();
		this.player1 = new Player("Joueur 1", 1000);
		this.StartHands();
	}
	
	/**
	 * Methode qui permet de commencer une partie.
	 */
	public void start() {
		this.pioche.melanger();
		Scanner sc = new Scanner(System.in);
	    System.out.println("Votre solde est de " + this.player1.getMoney());
	    System.out.println("Saisissez votre mise : ");
	    int mise = sc.nextInt();
	    while(mise > this.player1.getMoney()) {
	    	System.out.println("Vous n'avez pas assez, rentrez une autre mise: ");
		    mise = sc.nextInt();
	    }
	    this.player1.setMise(mise);
	    System.out.println("Votre mise est de " + this.player1.getMise());
	    System.out.println("Votre solde est de " + this.player1.getMoney());
	    this.player1.hand.ajoutDebut(this.pioche.tirerCarte(0));
	    this.player1.hand.ajoutDebut(this.pioche.tirerCarte(0));  
	    this.croupier.hand.ajoutDebut(this.pioche.tirerCarte(0));  
	    this.croupier.hand.ajoutDebut(this.pioche.tirerCarte(0));  
	    System.out.println(this.player1.hand);
	    System.out.println(valeurPaquet(this.croupier.hand));
	    System.out.println(this.croupier.hand);
	}

	/**
	 * Methode qui permet d'obtenir la pioche du Paquet.
	 * @return La pioche du Paquet.
	 */
	public Paquet getPioche() {
		return pioche;
	}
	
	/**
	 * Methode qui permet d'obtenir le croupier.
	 * @return Le croupier.
	 */
	public Croupier getCroupier() {
		return croupier;
	}

	/**
	 * Methode qui permet d'obtenir le joueur.
	 * @return Le joueur.
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * Methode qui permet de débuter le jeu pour le joueur et le croupier 
	 * en leur donnant deux cartes chacun.
	 */
	public void StartHands() {
		this.player1.CardToHand(this.pioche.tirerCarte(0));
		this.player1.CardToHand(this.pioche.tirerCarte(0));
		this.croupier.CardToHand(this.pioche.tirerCarte(0));
		this.croupier.CardToHand(this.pioche.tirerCarte(0));
	}
	
	/**
	 * Methode qui permet d'ajouter à la fin de la main du joueur une carte.
	 * @param p La carte à ajouter.
	 */
	public void addCard(Paquet p) {
		p.ajoutFin(this.pioche.tirerCarte(0));
	}
	
	/**
	 * Methode qui permet de calculer la valeur du Paquet.
	 * @param p Le Paquet dont on veut calculer la valeur.
	 * @return La valeur du Paquet.
	 */
	public int valeurPaquet(Paquet p) {
		int valeur=0;
		for(Carte c : p.getPaquet()) {
			switch(c.getValue()) {
			case "As":
				if(valeur+11>21) {
					valeur+=1;
					break;
				}
				valeur+=11;
				break;
			case "10":
			case "Valet":
			case "Dame":
			case "Roi":
				valeur+=10;
				break;
			default :
				valeur+=Integer.valueOf(c.getValue());
				break;
			}
		}
		return valeur;
	}
	
	/**
	 * Methode qui permet de savoir la valeur de la dernière carte du Croupier.
	 * @return la valeur de la carte.
	 */
	public int valeurLastCardCroupier() { 
		Paquet tmp = new Paquet(); // On crée un paquet temporaire
		tmp.ajoutFin(this.getCroupier().getHand().getCarte(1)); // On ajoute la deuxième carte du croupier
		return this.valeurPaquet(tmp); // On retourne la valeur de la carte
	}
	
	/* Retourne le vainqueur*/
	/**
	 * Methode qui permet de savoir qui est le vainqueur.
	 * @return Le vainqueur.
	 */
	public Personne rester() {
		while (valeurPaquet(this.croupier.getHand()) < 17){
			addCard(this.croupier.getHand());
		}
		
		int valueCroupier = this.valeurPaquet(this.croupier.getHand());
        int valuePlayer = this.valeurPaquet(this.player1.getHand());
        
		if(valuePlayer == 21) {
			this.player1.changer();
			System.out.println("BlackJack !!! CR");
			this.player1.setMiseInSoldePlayer("win");
			return this.player1;
		} else if (valueCroupier == 21) {
			this.croupier.changer();
			System.out.println("BlackJack !!! PL");
			return this.croupier;
		} else if(valueCroupier > 21 && valuePlayer > 21) {
			System.out.println("Egalité");
			this.player1.setMiseInSoldePlayer("equality");
			return null;
		} else if (valueCroupier > 21) {
			System.out.println("Le croupier a perdu");
			this.player1.changeHasWin();
			this.player1.setMiseInSoldePlayer("win");
			return this.player1;
		} else if (valuePlayer > 21) {
			System.out.println("Le joueur a perdu");
			this.croupier.changeHasWin();
			return this.croupier;
		} else if (valueCroupier > valuePlayer) {
			System.out.println("Le croupier a gagné");
			this.croupier.changeHasWin();
			return this.croupier;
		} else if(valueCroupier < valuePlayer) {
			System.out.println("vous avez gagné");
			this.player1.changeHasWin();
			this.player1.setMiseInSoldePlayer("win");
			return this.player1;
		} else {
			System.out.println("Egalité");
			this.player1.setMiseInSoldePlayer("equality");
			return null;
		}
	}
	
	/**
	 * Methode qui conclu le tour du joueur.
	 */
	public void endOfTurn() { 
        for(Carte carte : this.player1.getHand().getPaquet()) {
                this.pioche.ajoutFin(carte);
        }
        for(Carte carte : this.croupier.getHand().getPaquet()) {
                this.pioche.ajoutFin(carte);
        }
        
        this.player1.initHand();
        this.croupier.initHand();
        this.StartHands();
	}
	
	/**
	 * Methode qui represente la fin du jeu.
	 * @return le nouveau Blackjack.
	 */
	public Blackjack endOfGame() {
		return new Blackjack(); 
	}
	
	
	
}
