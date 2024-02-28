package view;

import cartes.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class VueCarte extends JPanel{
	
	protected Carte carte;
	
	/**
	 * Vue d'une carte face visible
	 * @param carte: carte à afficher
	 */
	public VueCarte(Carte carte) {
		super.setPreferredSize(new Dimension(100,180));
		super.setOpaque(false);
		super.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		super.setBackground(Color.white);
		JLabel value = new JLabel(carte.getValue());
		value.setFont(new Font("Serial", Font.BOLD, 20));
		JLabel color = new JLabel(carte.getColor());
		color.setFont(new Font("Serial", Font.PLAIN, 20));
		switch(carte.getColor()) {
			case "Coeur":
			case "Carreau":
				value.setForeground(Color.red);
				color.setForeground(Color.red);
				super.setBorder(new RoundedBorder(Color.red, 10, 10, false));
				break;
			case "Pique":
			case "Trèfle":
				super.setBorder(new RoundedBorder(Color.black, 10, 10, false));
				break;
		}
		value.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));
		super.add(value);
		color.setBorder(BorderFactory.createEmptyBorder(0,5,5,0));
		super.add(color);
	}
	
	/**
	 * Vue d'une carte face cachée, carte de dos et la pioche
	 */
	public VueCarte() {
		super.setPreferredSize(new Dimension(100,180));
		super.setLayout(new BorderLayout());
		super.setBackground(Color.white);
		super.setBorder(new RoundedBorder(Color.black, 10, 10, true));
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		   g.setColor(Color.white);
		   g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
	}

}