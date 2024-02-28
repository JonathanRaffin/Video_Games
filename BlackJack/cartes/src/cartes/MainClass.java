package cartes;

import paquet.*;
import view.*;

public class MainClass {

	public static void main(String[] args) {
		
		String info = "Interface GUI, juste utile pour débuguer certains points,"
				+ "Les packages utilisés sont cartes, paquet et view."
				+ "Dans view, les seules classes 'utiles' sont pour la vue de Carte et de Paquet";
		
		System.out.println(info);
		
		 Paquet paquet32 = Factory.paquet32();
		 Paquet main = new Paquet();
		
		 new GUI(paquet32, main);
		
	}

}