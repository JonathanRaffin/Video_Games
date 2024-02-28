package view;

import cartes.*;
import paquet.*;

public class VuePaquetVisible extends VuePaquet{
	
	protected Paquet paquet;

	/**
	 * Constructeur d'une Vue d'un Paquet Visible.
	 * @param paquet: paquet à afficher
	 */
	public VuePaquetVisible(Paquet paquet) {
		this.paquet = paquet;
		
		for(Carte carte : this.paquet.getPaquet()) {
			super.zoneCartes.add(new VueCarte(carte));
		}
	}
	
	/**
	 * Affiche la dernière carte du paquet.
	 */
	public void displayEnd() {
		super.zoneCartes.add(new VueCarte(this.paquet.getPaquet().get(this.paquet.getPaquet().size()-1)));
	}

}