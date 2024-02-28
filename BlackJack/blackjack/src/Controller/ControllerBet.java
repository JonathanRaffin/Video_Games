package Controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;
import GUI.*;

public class ControllerBet implements MouseInputListener{
		
		protected GUI GUI;
		protected JLabel token;
		protected String index;
		
		/**
		 * Contrôleur pour les jetons pour que l'utilisateur mise
		 * @param GUI
		 * @param token : un des différents jetons
		 * @param index : aide pour distinguer les jetons
		 */
		public ControllerBet(GUI GUI, JLabel token, String index) {
			this.GUI = GUI;
			this.token = token;
			this.index = index;
		}

		/**
		 * Lors d'un click sur une des jetons, ont mais la mide du joueur à jour.
		 * Sa solde se mettra à jour automatiquement.
		 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Point coord = new Point(arg0.getX(), arg0.getY());
			if(this.token.contains(coord)) {
				switch(this.index) {
				case "Jeton 1":
					this.GUI.blackjack.getPlayer1().setMise(1);
					break;
				case "Jeton 5":
					this.GUI.blackjack.getPlayer1().setMise(5);
					break;
				case "Jeton 25":
					this.GUI.blackjack.getPlayer1().setMise(25);
					break;
				case "Jeton 50":
					this.GUI.blackjack.getPlayer1().setMise(50);
					break;
				case "Jeton 100":
					this.GUI.blackjack.getPlayer1().setMise(100);
					break;
				case "Jeton 500":
					this.GUI.blackjack.getPlayer1().setMise(500);
					break;
				case "Jeton 1000":
					this.GUI.blackjack.getPlayer1().setMise(1000);
					break;
				}
				this.GUI.contentPane.removeAll();
				this.GUI.contentPane.add(this.GUI.getNewBet());
				this.GUI.pack();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}

		@Override
		public void mouseDragged(MouseEvent arg0) {}

		@Override
		public void mouseMoved(MouseEvent arg0) {}
}
