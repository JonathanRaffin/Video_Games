package blackjack;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;
import paquet.Paquet;

/**
 * Classe représentant le Factory.
 */
public class Factory {
	
	public Factory() {}
	
	/**
	 * Créer un paquet de 5 paquets.
	 * @return un paquet de 5 paquets.
	 */
	public static Paquet pioche(){
		List<String> color = new ArrayList<String>() {{  
			add("Trèfle"); add("Pique"); 
			add("Carreau"); add("Coeur"); }};
		List<String> value = new ArrayList<String>() {{  
			add("As"); add("2");
			add("3"); add("4");
			add("5"); add("6");
			add("7"); add("8");
			add("9"); add("10");
			add("Valet"); add("Dame");
			add("Roi"); }};
		List<Carte> liste = new ArrayList<>();
		for(int i=0; i<5; i++) {
			for(String couleur : color) {
				for(String valeur : value) {
					Carte tmpCard = new Carte(valeur, couleur);
					liste.add(tmpCard);
				}
			}
		}
		Paquet paquet = new Paquet(liste);
		return paquet;
	}
	
}
