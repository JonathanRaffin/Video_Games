package view;

import paquet.Paquet;

import java.awt.BorderLayout;

import control.Control;

public class VuePaquetCache extends VuePaquet{
	
	protected Paquet paquet;
	protected Control control;

	/**
	 * Constructeur d'une Vue d'un PaquetCachee
	 * @param paquet: paquet à afficher
	 * @param control: controle liée au paquet
	 */
	public VuePaquetCache(Paquet paquet, Control control) {
		this.paquet = paquet;
		VueCarte tmp = new VueCarte();
		tmp.addMouseListener(control);
		super.zoneCartes.add(tmp, BorderLayout.CENTER);
	}

}