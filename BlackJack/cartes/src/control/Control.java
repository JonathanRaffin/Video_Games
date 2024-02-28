package control;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import view.*;

public class Control implements MouseInputListener {
	
	protected GUI gui;
	
	public Control(GUI gui) {
		this.gui = gui;
	}

	/**
	 * Lors d'un clique sur la pioche, on ajoute une carte à la main du joueur
	 * et on l'affiche.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int nb = this.gui.paquet.getPaquet().size()-1;
		if(nb != -1) {
			this.gui.main.ajoutFin(this.gui.paquet.tirerCarteRandom(nb));			
			this.gui.init();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

}