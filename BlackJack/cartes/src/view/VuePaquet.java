package view;

import java.awt.*;
import javax.swing.*;

public abstract class VuePaquet extends JPanel{

	public JPanel zoneCartes;
	
	/**
	 * Constructeur d'une Vue d'un Paquet.
	 */
	public VuePaquet() {
		this.zoneCartes = new JPanel();
		this.init();
	}
	
	/**
	 * Initialise les zones de la vue.
	 */
	public void init() {
		super.setLayout(new BorderLayout());
		this.zoneCartes.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.zoneCartes.setBackground(new Color(0,128,55));
		this.zoneCartes.setAlignmentX(0.5f);
		this.zoneCartes.setAlignmentY(0.5f);
		super.add(this.zoneCartes, BorderLayout.CENTER);
	}

}