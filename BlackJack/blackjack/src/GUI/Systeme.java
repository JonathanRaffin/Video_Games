package GUI;

import java.awt.*;
import javax.swing.*;
import Controller.*;

public class Systeme {
	
	public Systeme() {}
	
	/**
	 * Initialise un bouton: son texte, sa taille, sa couleur.
	 * @param btn : bouton à modifier
	 * @param text : texte du bouton
	 * @param color : couleur du bouton
	 */
	public static void initButton(JPanel btn, String text, Color color) {
		JLabel textBtn = new JLabel(text);
		textBtn.setForeground(Color.white);
		textBtn.setFont(new Font("Serif", Font.BOLD, 20));
		textBtn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        btn.setBorder(BorderFactory.createLineBorder(Color.black));
        btn.setPreferredSize(new Dimension(175,75));
        btn.setBackground(color);
        btn.add(textBtn);
	}
	
	/**
	 * Initialise un bouton: son texte, sa taille.
	 * @param btn : bouton à modifier
	 * @param text : texte du bouton
	 */
	public static void initButton(JPanel btn, String text) {
		Systeme.initButton(btn, text, Systeme.getButtonColor());
	}
	
	/**
	 * Ajoute une image dans un JLabel
	 * @param area : JLabel contenant l'image
	 * @param picture : l'image à afficher
	 * @param dimension : la dimension de l'image
	 * @param center : si l'image doit être centrée horizontalement ou pas
	 */
	public static void initPictures(JLabel area, String picture, Dimension dimension, boolean center) {
		ImageIcon img = new ImageIcon(picture);
        area.setPreferredSize(dimension);
        if(center == true) {
        	area.setHorizontalAlignment(JLabel.CENTER);        	
        }
        area.setVerticalAlignment(JLabel.CENTER);
        area.setIcon(img);
	}
	
	/**
	 * Ajoute une image dans un JLabel
	 * @param area : JLabel contenant l'image
	 * @param picture : l'image à afficher
	 * @param dimension : la dimension de l'image
	 */
	public static void initPictures(JLabel area, String picture, Dimension dimension) {
		Systeme.initPictures(area, picture, dimension, false);
	}
	
	/**
	 * Initialise un JLabel
	 * @param comp : JLabel à modifier
	 * @param text : texte du JLabel
	 */
	public static void initLabel(JLabel comp, String text) {
		Systeme.initLabel(comp, text, 40);
	}
	
	/**
	 * Initialise un JLabel
	 * @param comp : JLabel à modifier
	 * @param text : texte du JLabel
	 * @param taille : taille du texte
	 */
	public static void initLabel(JLabel comp, String text, int taille) {
		comp.setText(text);
		comp.setForeground(Color.white);
		comp.setFont(new Font("Serif", Font.BOLD, taille));
	}
	
	/**
	 * @return la couleur du fond du jeu.
	 */
	public static Color getBackgroundColor() {
		return new Color(0,128,55);
	}

	/**
	 * @return la couleur d'un bouton.
	 */
	public static Color getButtonColor() {
		return new Color(0,184,219);
	}

	/**
	 * @return la couleur d'un bouton lors du survol de la souris.
	 */
	public static Color getButtonColorDark() {
		return new Color(0,149,178);
	}
	
	/**
	 * Créer une zone vide. Juste utile pour centrer des composants en swing
	 * @param dimension : dimension de la zone
	 * @return la zone
	 */
	public static JPanel blankArea(Dimension dimension) {
		JPanel blankArea = new JPanel();
		blankArea.setPreferredSize(dimension);
		blankArea.setBackground(Systeme.getBackgroundColor());
		return blankArea;
	}
	
	/**
	 * Ajoute le Contrôleur du GUI à un bouton
	 * @param GUI
	 * @param btn : bouton où l'on ajoute le contrôleur
	 * @param text : texte du bouton
	 */
	public static void initButtonControl(GUI GUI, JPanel btn, String text) {
		Systeme.initButton(btn, text);
		ControllerGUI controller = new ControllerGUI(GUI, btn, text);
        btn.addMouseListener(controller);
	}
	
	/**
	 * Initilise les contraintes d'un Layout, Utile juste pour du swing.
	 * @param value : espace entre chaque composant
	 * @return les contraintes du layout
	 */
	public static GridBagConstraints getGBC(int value) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(value, 0, value, 0);
		return gbc;
	}
	
	/**
	 * Initialise le texte qui affiche les point de chaque Personne pour l'interface Play
	 * @param comp : JLabel à modifier
	 * @param name : Nom de la personne
	 * @param value : valeur des points de son paquet de cartes
	 */
	public static void initLabelNbPoint(JPanel comp, String name, int value) {
		JPanel tmp = new JPanel();
		JLabel text = new JLabel();
		Systeme.initLabel(text, name + ": " + String.valueOf(value));
		tmp.add(text);
		tmp.setBackground(new Color(0,95,41));
		
		comp.setLayout(new GridBagLayout());
		comp.add(tmp, Systeme.getGBC(30));
		comp.setBackground(Systeme.getBackgroundColor());
		comp.setPreferredSize(new Dimension(350,180));
	}

}
