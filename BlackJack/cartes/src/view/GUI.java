package view;

import control.Control;
import paquet.*;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
	
		protected Container contentPane;
		public Paquet paquet;
		public Paquet main;
		protected Control control;
		protected VuePaquetVisible vueMain;
		protected VuePaquetCache vuePaquet;

		/**
		 * Constructeur de l'Interface Graphique (GUI).
		 * @param paquet: Paquet entier (pioche)
		 * @param main: main du joueur
		 */
	    public GUI (Paquet paquet, Paquet main) {
	        super("Cartes");
	        super.setPreferredSize(new Dimension(1200, 800));
	        this.contentPane = getContentPane();       
	        this.contentPane.setBackground(new Color(0,128,55));
	        this.contentPane.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = GridBagConstraints.RELATIVE;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(20, 0, 20, 0);
	        
	        this.paquet = paquet;
	        this.main = main;
	        this.control = new Control(this);
	        
	        this.vuePaquet = new VuePaquetCache(this.paquet, this.control);
	        this.vueMain = new VuePaquetVisible(this.main);
	        this.contentPane.add(this.vuePaquet, gbc);
	        this.contentPane.add(this.vueMain, gbc);
	        
	        super.pack();
	        super.setVisible(true);
	        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    
	    /**
	     * Met à jour l'affichage de la main du joueur
	     */
	    public void init() {
	    	this.vueMain.displayEnd();
	        super.pack();
	    }
	    
}