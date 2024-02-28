package paquet;

import java.util.*;
import cartes.Carte;

public class Factory {
	
	public Factory() {}
	
	/**
	 * Créer un paquet de 52 Cartes.
	 * @return un paquet de 52 cartes.
	 */
	public static Paquet paquet52(){
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
		for(String couleur : color) {
			for(String valeur : value) {
				Carte tmpCard = new Carte(valeur, couleur);
				liste.add(tmpCard);
			}
		}
		Paquet paquet = new Paquet(liste);
		return paquet;
	}
	
	/**
	 * Créer un paquet de 32 Cartes.
	 * @return un paquet de 32 cartes.
	 */
	public static Paquet paquet32(){
		List<String> color = new ArrayList<String>() {{  
			add("Trèfle"); add("Pique"); 
			add("Carreau"); add("Coeur"); }};
		List<String> value = new ArrayList<String>() {{  
			add("As"); add("7"); 
			add("8"); add("9"); 
			add("10"); add("Valet"); 
			add("Dame"); add("Roi"); }};
		List<Carte> liste = new ArrayList<>();
		for(String couleur : color) {
			for(String valeur : value) {
				Carte tmpCard = new Carte(valeur, couleur);
				liste.add(tmpCard);
			}
		}
		Paquet paquet = new Paquet(liste);
		return paquet;
	}
	
	

}
