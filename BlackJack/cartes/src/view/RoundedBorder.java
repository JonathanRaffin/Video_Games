package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

// Classe inspirée d'ici: https://www.developpez.net/forums/d249953/java/interfaces-graphiques-java/awt-swing/agents-placement-fenetres/bordure-arrondie-jpanel/
// Classe récupérée pour l'arrondi de la bordure, mais l'ajout de la croix fait par nous.
 
public class RoundedBorder implements Border {  
 
	private Color couleur;
	private int largeurArc;
	private int hauteurArc;
	private boolean cross;
 
	/**
	 * Constructeur d'une bordure avec des coins arrondis pour les cartes.
	 * @param couleur: couleur  de la bordure
	 * @param largeurArc: largeur de l'arrondi
	 * @param hauteurArc: hauteur de l'arrondi
	 * @param cross: ajout de la croix ou non pour la pioche ou les cartes normales
	 */
	public RoundedBorder(Color couleur, int largeurArc, int hauteurArc, boolean cross)  {
		this.couleur = couleur;
		this.largeurArc = largeurArc;
		this.hauteurArc = hauteurArc;
		this.cross = cross; 
	} 
 
	/**
	 * Dessine la bordure
	 */
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)  {
		int adjustWH = 1;//idem pour width et height
		g.setColor(this.couleur);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRoundRect(x, y, width-adjustWH, height-adjustWH, this.largeurArc, this.hauteurArc);
        if(this.cross == true) {
        	g.setColor(Color.black);
            ((Graphics2D) g).setStroke(new BasicStroke(1.5f));
            g.drawLine(80, 15, 15, 160);
            g.drawLine(15, 15, 80, 160);
        }
	}  
 
	public Insets getBorderInsets(Component c)  {  
		return new Insets(0,0,0,0); 
	}  
 
	public boolean isBorderOpaque()  {
		return true; 
	} 
 
}