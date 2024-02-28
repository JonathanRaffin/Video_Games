package GUI;

import java.awt.*;
import javax.swing.*;
import blackjack.*;

public class GUI extends JFrame{
	
	public Blackjack blackjack;
	public Container contentPane;
	
	/**
	 * Construit la fenêtre du jeu.
	 * @param blackjack: le modèle.
	 */
	public GUI(Blackjack blackjack) {
		super("BLACKJACK");
        super.setMinimumSize(new Dimension(1200, 800));
        
        this.contentPane = getContentPane();   
        this.blackjack = blackjack;
        this.contentPane.add(this.getNewMenu());
        
        Image imgLogo = Toolkit.getDefaultToolkit().getImage("pictures/Logo.png");
        super.setIconImage(imgLogo);        

        super.pack();
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Créer une nouvelle interface du Menu.
	 * @return un Menu
	 */
	public Menu getNewMenu() {
		return new Menu(this);
	}
	
	/**
	 * Créer une nouvelle interface des Règles.
	 * @return les Règles
	 */
	public Rules getNewRules(String text) {
		return new Rules(this, text);
	}
	
	/**
	 * Créer une nouvelle interface pour miser.
	 * @return l'affichage pour miser
	 */
	public Bet getNewBet() {
		return new Bet(this);
	}
	
	/**
	 * Créer une nouvelle interface du Jeu.
	 * @return un Jeu
	 */
	public Play getNewPlay() {
		return new Play(this);
	}
	
	/**
	 * Créer une nouvelle interface du Resultats.
	 * @return un affichage de Resultats
	 */
	public Info getNewInfo(Personne personne) {
		return new Info(this, personne);
	}
	
}
