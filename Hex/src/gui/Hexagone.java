package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Hexagone extends JPanel {
	
	/* Coordonées du départ du dessin de l'Hexagone */
	protected int startX;
	protected int startY;
	protected Color couleur;
	/* Position dans la grille pour déterminer sa bordure */
	protected String position;
	/* Taille du plateau de jeu, pour calculer la taille de chaque hexagone */
	protected int taille;
	
	/**
	 * Création d'un Hexagone
	 * @param startX : Coordonées x du début du tracé
	 * @param startY : Coordonées y du début du tracé
	 * @param color : couleur de l'hexagone
	 * @param position : permet de savoir ou il se situe sur la grille 
	 */
	public Hexagone(int startX, int startY, Color color, String position, int taille) {
		this.startX = startX;
		this.startY = startY;
		this.couleur = color;
		this.position = position;
		this.taille = taille;
	}
	
	/**
	 * Création d'un Hexagone
	 * @param startX : Coordonées x du début du tracé
	 * @param startY : Coordonées y du début du tracé
	 * @param position : permet de savoir ou il se situe sur la grille 
	 */
	public Hexagone(int startX, int startY, String position, int taille) {
		this(startX, startY, Color.white, position, taille);
	}

	/**
	 * @return la couleur de l'Hexagone
	 */
	public Color getColor() {
		return this.couleur;
	}
	
	/**
	 * Modifie la couleur de l'Hexagone
	 * @param color
	 */
	public void setColor(Color color) {
		this.couleur = color;
	}

	public ArrayList<int[]> tmp() {
		ArrayList<int[]> tmp = new ArrayList<int[]>();
		if(this.taille > 15){
			int[] x = {this.startX+17, this.startX+35, this.startX+35, this.startX+17, this.startX, this.startX};
			int[] y = {this.startY, this.startY+7, this.startY+35, this.startY+42, this.startY+35, this.startY+7};
			tmp.add(x);
			tmp.add(y); 
		} else {
			int[] x = {this.startX+25, this.startX+50, this.startX+50, this.startX+25, this.startX, this.startX};
			int[] y= {this.startY, this.startY+10, this.startY+50, this.startY+60, this.startY+50, this.startY+10};
			tmp.add(x);
			tmp.add(y); 
		}
		return tmp;
	}
	
	/**
	 * Redessine le JPanel en Hexagone, avec une bordure
	 */
	public void paint(Graphics g){
        g.setColor(this.couleur);
		
		ArrayList<int[]> tmp = this.tmp();
		int[] x = tmp.get(0);
		int[] y = tmp.get(1);

	    int[] ligne1 = {x[0], y[0], x[1], y[1]};
		int[] ligne2 = {x[1], y[1], x[2], y[2]};
	    int[] ligne3 = {x[2], y[2], x[3], y[3]};
	    int[] ligne4 = {x[3], y[3], x[4], y[4]};
	    int[] ligne5 = {x[4], y[4], x[5], y[5]};
	    int[] ligne6 = {x[5], y[5], x[0], y[0]};
	    
	    int[][] haut = {ligne1, ligne6, ligne2, ligne3, ligne4, ligne5};
	    int[][] bas = {ligne3, ligne4, ligne1, ligne2, ligne5, ligne6};
	    int[][] gauche = {ligne4, ligne5, ligne1, ligne2, ligne3, ligne6};
	    int[][] droite = {ligne1, ligne2, ligne3, ligne4, ligne5, ligne6};
	    
	    g.fillPolygon(x, y, 6);
	    switch(position) {
	    case "h":
	    	this.bordure(g, Color.red, haut);
	    	break;
	    case "b":
	    	this.bordure(g, Color.red, bas);
	    	break;
	    case "g":
	    	this.bordure(g, Color.blue, gauche);
	    	break;
	    case "d":
	    	this.bordure(g, Color.blue, droite);
	    	break;
	    case "hg":
	    	this.bordureCoin4(g, ligne1, ligne6, ligne4, ligne5, ligne2, ligne3);
	    	break;
	    case "bd":
	    	this.bordureCoin4(g, ligne3, ligne4, ligne1, ligne2, ligne5, ligne6);
	    	break;
	    case "hd":
	    	this.bordureCoin3(g, ligne1, ligne6, ligne2, ligne3, ligne4, ligne5);
	    	break;
	    case "bg":
	    	this.bordureCoin3(g, ligne3, ligne4, ligne5, ligne1, ligne2, ligne6);
	    	break;
	    default:
	    	this.bordureNormal(g, x, y);
	    	break;
	    }
	}
	
	/**
	 * Ajoute une bordure d'une couleur sur un côté de la grille
	 * @param g1 : l'Hexagone
	 * @param couleur : couleur de la bordure
	 * @param liste : liste des côtés de l'Hexagone à dessiner
	 */
	public void bordure(Graphics g1, Color couleur, int[][] liste) {
		BasicStroke line = new BasicStroke(3.0f);
	    Graphics2D g = (Graphics2D) g1;
	    g.setStroke(line);
		g.setColor(couleur);
		g.drawLine(liste[0][0], liste[0][1], liste[0][2], liste[0][3]);
		g.drawLine(liste[1][0], liste[1][1], liste[1][2], liste[1][3]);
		BasicStroke line2 = new BasicStroke(1.0f);
		g.setStroke(line2);
		g.setColor(Color.black);
		for(int i=2; i <= liste.length - 1; i++) {
			g.drawLine(liste[i][0], liste[i][1], liste[i][2], liste[i][3]);
		}
	}
	
	/**
	 * Ajoute une bordure pour un coin avec 4 côtés à modifier
	 * @param g1 : l'Hexagone
	 * @param l1 : Première bordure en rouge
	 * @param l2 : Deuxième bordure en rouge
	 * @param l3 : Troisième bordure en bleu
	 * @param l4 : Quatrième bordure en bleu
	 * @param l5 : Cinquième bordure en noir
	 * @param l6 : Sixième bordure en noir
	 */
	public void bordureCoin4(Graphics g1, int[] l1, int[] l2, int[] l3, int[] l4, int[] l5, int[] l6) {
		BasicStroke line = new BasicStroke(3.0f);
	    Graphics2D g = (Graphics2D) g1;
	    g.setStroke(line);
		g.setColor(Color.red);
		g.drawLine(l1[0], l1[1], l1[2], l1[3]);
		g.drawLine(l2[0], l2[1], l2[2], l2[3]);
		g.setColor(Color.blue);
		g.drawLine(l3[0], l3[1], l3[2], l3[3]);
		g.drawLine(l4[0], l4[1], l4[2], l4[3]);
		BasicStroke line2 = new BasicStroke(1.0f);
		g.setStroke(line2);
		g.setColor(Color.black);
		g.drawLine(l5[0], l5[1], l5[2], l5[3]);
		g.drawLine(l6[0], l6[1], l6[2], l6[3]);
	}
	
		/**
	 * Ajoute une bordure pour un coin avec 3 côtés à modifier
	 * @param g1 : l'Hexagone
	 * @param l1 : Première bordure en rouge
	 * @param l2 : Deuxième bordure en rouge
	 * @param l3 : Troisième bordure en bleu
	 * @param l4 : Quatrième bordure en noir
	 * @param l5 : Cinquième bordure en noir
	 * @param l6 : Sixième bordure en noir
	 */
	public void bordureCoin3(Graphics g1, int[] l1, int[] l2, int[] l3, int[] l4, int[] l5, int[] l6) {
		BasicStroke line = new BasicStroke(3.0f);
	    Graphics2D g = (Graphics2D) g1;
	    g.setStroke(line);
		g.setColor(Color.red);
		g.drawLine(l1[0], l1[1], l1[2], l1[3]);
		g.drawLine(l2[0], l2[1], l2[2], l2[3]);
		g.setColor(Color.blue);
		g.drawLine(l3[0], l3[1], l3[2], l3[3]);
		BasicStroke line2 = new BasicStroke(1.0f);
		g.setStroke(line2);
		g.setColor(Color.black);
		g.drawLine(l4[0], l4[1], l4[2], l4[3]);
		g.drawLine(l5[0], l5[1], l5[2], l5[3]);
		g.drawLine(l6[0], l6[1], l6[2], l6[3]);
	}
	
	/**
	 * Ajoute une bordure noire pour le centre de la grille
	 * @param g : l'Hexagone
	 * @param x[] : Coordonnées de chaque point x de l'Hexagone
	 * @param y[] : Coordonnées de chaque point y de l'Hexagone
	 */
	public void bordureNormal(Graphics g, int x[], int y[]) {
		g.setColor(Color.black);
		g.drawLine(x[0], y[0], x[1], y[1]);
		g.drawLine(x[1], y[1], x[2], y[2]);
	    g.drawLine(x[2], y[2], x[3], y[3]);
	    g.drawLine(x[3], y[3], x[4], y[4]);
	    g.drawLine(x[4], y[4], x[5], y[5]);
	    g.drawLine(x[5], y[5], x[0], y[0]);
	}

}