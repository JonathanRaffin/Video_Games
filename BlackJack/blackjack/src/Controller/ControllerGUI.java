package Controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import GUI.*;
import blackjack.Personne;

public class ControllerGUI implements MouseInputListener{
	
	protected GUI GUI;
	protected JPanel button;
	protected String index;
	
	/**
	 * Contrôleur permettant la gestion entre les différentes fenêtres
	 * @param GUI
	 * @param button : bouton qui actionne le contrôleur
	 * @param index : index vers quelle page doit renvoyer le contrôleur
	 */
	public ControllerGUI(GUI GUI, JPanel button, String index) {
		this.GUI = GUI;
		this.button = button;
		this.index = index;
	}

	/**
	 * Gestion de l'affichage des interfaces.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point coord = new Point(arg0.getX(), arg0.getY());
		if(this.button.contains(coord)) {
			System.out.println(this.index);
			/* Si on quitte le jeu */
			if(this.index == "Quitter") {
				this.GUI.dispose();
			} 
			/* Affiche les Règles */
			if(this.index == "Règles") {
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewRules("Menu"));
			} 
			/* Affiche le Menu */
			if(this.index == "Menu" || this.index == "Annuler") {
				int tmp = this.GUI.blackjack.getPlayer1().getMoney() + this.GUI.blackjack.getPlayer1().getMise();
				this.GUI.blackjack.getPlayer1().setMoney(tmp);
				this.GUI.blackjack.getPlayer1().initMise();
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewMenu());
			} 
			/* Affiche le menu et relance une nouvelle partie lors de la défaite du joueur */
			if(this.index == "Menu ") {
				this.GUI.blackjack = this.GUI.blackjack.endOfGame();
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewMenu());
			}
			/* Affiche l'interface pour miser et réinitialise les cartes des joueurs et leurs mises */
			if(this.index == "Jouer" || this.index == "Rejouer") {
				this.GUI.blackjack.getPlayer1().initMise();
				this.GUI.blackjack.getPlayer1().reinit();
				this.GUI.blackjack.getCroupier().reinit();
				this.GUI.blackjack.endOfTurn();
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewBet());
			} 
			/* Affiche l'interface du jeu après avoir misé */
			if(this.index == "Continuer") {
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewPlay());
			} 
			/* Annule la mise du joueur et remet à jour sa solde */
			if(this.index == "Reset") {
				int tmp = this.GUI.blackjack.getPlayer1().getMoney() + this.GUI.blackjack.getPlayer1().getMise();
				this.GUI.blackjack.getPlayer1().setMoney(tmp);
				this.GUI.blackjack.getPlayer1().initMise();
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewBet());
			}
			/* Mise toute la solde du joueur */
			if(this.index == "All In") {
				this.GUI.blackjack.getPlayer1().setMise(this.GUI.blackjack.getPlayer1().getMoney());
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewBet());
			}
			/* Pioche une carte */
			if(this.index == "Piocher") {
				if(this.GUI.blackjack.valeurPaquet(this.GUI.blackjack.getPlayer1().getHand()) < 21) {
					this.GUI.blackjack.addCard(this.GUI.blackjack.getPlayer1().getHand());										
				}
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewPlay());
			} 
			/* Affiche les résultats après une manche */
			if(this.index == "Rester") {
				Personne personne = this.GUI.blackjack.rester();
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewInfo(personne));
			}
			/* Actualise le GUI, pour afficher chaque changement d'interface */
			this.GUI.pack();
		}
	}

	/**
	 * Change de couleur lors du survol de la souris.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.button.setBackground(Systeme.getButtonColorDark());			
	}

	/**
	 * Change de couleur lors du survol de la souris.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.button.setBackground(Systeme.getButtonColor());			
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
}
