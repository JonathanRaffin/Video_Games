package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hex.Game;
import util.Pair;

public class Plateau extends JFrame implements ActionListener{
	
	/* Modèle du jeu */
	protected Game game;
	protected Menu menu;
	protected int taille;
	/* Attribut utile pour l'utilisation de swing */
	protected Container contentPane;
	protected JPanel zone, centre, haut, bas, parametres;
	protected JTextField selectX, selectY;
	protected JButton validate, returnMenu;
	/* Indication pour savoir quelle joueur doit jouer */
	protected String indicePlayer;
	protected Color indiceColor;
	protected String textAction;
	protected JLabel indPlayer, indAction;
	protected int bot;
	
	/**
	 * Création du GUI (en swing)
	 * @param game : Modele
	 */
	public Plateau(Game game, Menu menu, int bot) {
		/* Initialisation de la fenêtre */
		super("Hex");
        super.setMinimumSize(new Dimension(1400, 1000));
        this.contentPane = super.getContentPane();
        this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.LINE_AXIS));
        
		/* Implémentation du Menu */
		this.menu = menu;
		/* Implémentation du modèle */
        this.game = game;
        this.taille = this.game.getSize();
        // this.game.displayGrid();
        
		/* Utiles pour le GUI */
        this.zone = new JPanel();
        this.zone.setLayout(new BorderLayout());
		this.bot = bot;
        
		/* Système d'affichage pour la création du GUI */
		this.setNameColor();
        this.marge();
        this.grille();
        this.contentPane.add(zone);
		
		/* Affichage de la fenêtre */
        super.pack();
        super.setVisible(true);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Créer les marges autour de la grille
	 */
	public void marge() {
		/* Permet de centrer la grille  */
		int x = this.taille > 15 ? 35 : 50;
		int y = this.taille > 15 ? 38 : 55;
		int margeX = ((1400 - ((this.taille + (this.taille / 2)) * x)) / 2) - 20;
		int margeY = ((1000 - (this.taille * y)) / 2) - 20;
		
		/* Zone du "haut" avec la sélection du choix de la case pour le joueur */
		this.haut = Systeme.initJPanel(1400, margeY);
		this.haut.setLayout(new GridLayout(2, 0));

		/* Création d'une des 2 parties du haut */
		JPanel informations = new JPanel();
		informations.setLayout(new FlowLayout());
		
		if(this.bot == 0){
			/* Création d'une des 2 parties du haut */
			JPanel parametres = new JPanel();
			parametres.setLayout(new FlowLayout());
			/* Indique quelle joueur doit jouer */
			this.indPlayer = new JLabel(this.indicePlayer);
			this.indPlayer.setForeground(this.indiceColor);
			parametres.add(this.indPlayer);
			/* Zone de texte pour jouer le coup x */
			this.selectX = new JTextField("1", 8);
			parametres.add(Systeme.initJLabel("Valeur x:", false));
			parametres.add(this.selectX);
			/* Zone de texte pour jouer le coup x */
			this.selectY = new JTextField("1", 8);
			parametres.add(Systeme.initJLabel("Valeur y:", false));
			parametres.add(this.selectY);
			/* Bouton "Valider" pour jouer le coup */
			this.validate = new JButton("Valider");
			this.validate.addActionListener(this);
			parametres.add(this.validate);	
			this.haut.add(parametres);
		}
		
		/* Ajoute les informations sur la deuxième partie si besoin */
		this.indAction = new JLabel(this.textAction);
		informations.add(this.indAction);
		this.haut.add(informations);

		/* Zone du "bas" avec un bouton permettant le retour au menu */
		this.bas = Systeme.initJPanel(1400, margeY);
		this.returnMenu = new JButton("Retour au Menu");
		this.returnMenu.addActionListener(this);
		this.bas.add(this.returnMenu);
		
		this.zone.add(this.haut, BorderLayout.NORTH);
		this.zone.add(Systeme.initJPanel(margeX, 60), BorderLayout.WEST);
		this.zone.add(Systeme.initJPanel(margeX, 60), BorderLayout.EAST);
		this.zone.add(this.bas, BorderLayout.SOUTH);
		
	}	
	
	/**
	 * Création de la grille en reprenant le modèle,
	 * avec un décalage pour l'affichage et un système de bordures
	 */
	public void grille() {
		this.centre = new JPanel();
		this.centre.setLayout(null);

		double x = 10;
		double y = 10;
		double decalage = 10;
		int width = 0; int height = 0;
		if(this.taille > 15){
			height = 45 * this.taille;
			width = 31 * this.taille;
		} else {
			height = 60 * this.taille;
			width = 51 * this.taille;
		}
		int size = this.taille - 1;
		
		for(int i=0; i <= size; i++) {
			for(int j=0; j <= size; j++) {
				/* Détermine le placement et ainsi la bordure à dessiner */
				String position = "n";
				if(i == 0 && j != 0 && j != size) {
					position = "h";
				} if(i == size && j != 0 && j!= size) {
					position = "b";
				} if(j == 0 && i != 0 && i != size) {
					position = "g";
				} if(j == size && i != 0 && i != size) {
					position = "d";
				} if(i == 0 && j == 0) {
					position = "hg";
				} if(i == size && j == size) {
					position = "bd";
				} if(i == size && j == 0) {
					position = "bg";
				} if(i == 0 && j == size) {
					position = "hd";
				}
				Hexagone hexa = new Hexagone((int)x,(int)y, position,this.taille);
				hexa.setBounds((int)x, (int)y, width, height);
				/* Colorie les hexagones, si besoin */
				switch(this.game.getGrid()[i][j]) {
				case 1:
					hexa.setColor(Color.red);
					break;
				case 2:
					hexa.setColor(Color.blue);
					break;
				}
				this.centre.add(hexa);
				x += this.taille > 15 ? 17 : 25;
				// x += 15;
			}
			// y += 25;
			y += this.taille > 15 ? 17 : 25;
			decalage += this.taille > 15 ? 8 : 12;
			x = decalage;
		}
		this.zone.add(this.centre, BorderLayout.CENTER);
	}

	/**
	 * Permet de savoir quelle joueur doit jouer.
	 * La valeur change entre le joueur Rouge et Bleu à chaque tour.
	 */
	public void setNameColor() {
		if (this.game.getCurrentPlayer() == this.game.getPlayer1()) {
			this.indiceColor = Color.red;
			this.indicePlayer = "Joueur Rouge: ";
		} else {
			this.indiceColor = Color.blue;
			this.indicePlayer = "Joueur Bleu: ";
		}
	}

	/**
	* Action des 2 Boutonsde l'interface:
	* "Valider": Colorie un Hexagone aux coordonées indiquées par le joueur
	* "Retour au Menu": Met fin à la partie et affiche le menu
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.validate){
			this.textAction = "";
			int winner = this.game.play(Integer.parseInt(this.selectY.getText())-1, Integer.parseInt(this.selectX.getText())-1);
			if (winner > 0) {
				this.textAction = "Le gagnant est le joueur " + winner + " !";
				this.validate.setEnabled(false);
			}
			else {
				if (winner == -1) this.textAction = "Coordonnées incorrectes.";
				else if (winner == -2) this.textAction = "Impossible de jouer le coup car la case est déjà occupée.";
			}
			this.update();
		} else if (e.getSource() == this.returnMenu){
			this.menu.setVisible(true);
			super.dispose();
		}
	}

	public void play() {
		int winner = 0;
        while(winner < 1) {
            Pair p = game.getCurrentPlayer().play(game);
            winner = game.play(p.getP1(),p.getP2());
            game.displayGrid();
            this.update();
        }
		this.textAction = "Le gagnant est le joueur " + winner + " !";
		this.update();
	}
	
	/**
	 * Met à jour le GUI, avec le modèle modifié
	 */
	public void update() {
		this.zone.remove(this.centre);
		if(this.bot == 0){
			this.setNameColor();
			this.indPlayer.setText(this.indicePlayer);
			this.indPlayer.setForeground(this.indiceColor);
		}
		this.indAction.setText(this.textAction);
		this.grille();
		super.pack();
	}

}