package paquet;
import cartes.Carte;
import java.util.*;

public class Paquet {
	
	protected List<Carte> paquet;

	/**
	 * Créer un paquet.
	 * @param paquet
	 */
	public Paquet(List<Carte> paquet) {
		this.paquet = paquet;
	}
	
	/**
	 * Créer un paquet vide.
	 */
	public Paquet() {
		this(new ArrayList<>());
	}

	/**
	 * @return le paquet.
	 */
	public List<Carte> getPaquet() {
		return this.paquet;
	}
	
	/**
	 * Change le paquet par un nouveau.
	 * @param newPaquet : nouveau paquet.
	 */
	public void setPaquet(List<Carte> newPaquet) {
		this.paquet = newPaquet;
	}

	/**
	 * Affiche un paquet.
	 */
	@Override
	public String toString() {
		return "Paquet = " + this.paquet;
	}
	
	/**
	 * Ajoute une carte au début du paquet.
	 * @param carte : carte rajoutée.
	 */
	public void ajoutDebut(Carte carte) {
		List<Carte> tmp = this.paquet;
		this.paquet = new ArrayList<>();
		this.paquet.add(carte);
		for(Carte card : tmp) {
			this.paquet.add(card);			
		}
	}
	
	/**
	 * Ajoute une carte à la fin du paquet.
	 * @param carte : carte rajoutée.
	 */
	public void ajoutFin(Carte carte) {
		this.paquet.add(carte);
	}
	
	/**
	 * Tire une valeur aléatoirement.
	 * @param nb : max de la valeur à prendre.
	 * @return le nombre aléatoire.
	 */
	public int valeurAleatoire(int nb) {
		Random rand = new Random(); 
		return rand.nextInt(nb - 0 + 1) + 0;
	}
	
	/**
	 * Choisie une carte aléatoire du paquet et la supprime du paquet.
	 * @param nb : taille de la liste.
	 * @return une carte choisie aléatoirement.
	 */
	public Carte tirerCarteRandom(int nb) {
		int nbAlea = this.valeurAleatoire(nb);
		Carte carte = this.getPaquet().get(nbAlea);
		this.paquet.remove(nbAlea);
		return carte;
	}
	
	/**
	 * Choisie une carte, via l'index, du paquet et la supprime du paquet.
	 * @param index : index de la carte à retourner.
	 * @return une carte.
	 */
	public Carte tirerCarte(int index) {
		Carte carte = this.paquet.get(index);
		this.paquet.remove(index);
		return carte;
	}
	
	/**
	 * Choisie une carte, via l'index, du paquet et ne la supprime pas du paquet.
	 * @param index : index de la carte à retourner.
	 * @return une carte.
	 */
	public Carte getCarte(int index) {
		return this.paquet.get(index);
	}
	
	/**
	 * Melange le paquet.
	 */
	public void melanger() {
		List<Carte> tmp = this.paquet;
		List<Carte> newList = new ArrayList<>();
		int nb = tmp.size()-1;
		int compt = 0;
		for(int i=nb; i>=0; i--) {
			compt += 1;
			Carte carte = this.tirerCarteRandom(i);
			newList.add(carte);
		}
		this.paquet = newList;
	}
	
	/**
	 * Coupe le paquet en 2 et met la 2ièm partie au dessus du paquet.
	 */
	public void couper() {
		int nb = this.paquet.size()-1;
		List<Integer> value = new ArrayList<>() {{
			add(0);add(1);add(2);add(3);
			add(nb-3);add(nb-2);add(nb-1);add(nb);}};
		int nbAlea = 4;
		while(value.contains(nbAlea)) {
			nbAlea = this.valeurAleatoire(nb);			
		}
		List<Carte> firstPart = new ArrayList<>();
		List<Carte> secondPart = new ArrayList<>();
		for(int i=nbAlea; i<=nb; i++) {
			secondPart.add(this.getCarte(i));
		}
		for(int i=0; i<nbAlea; i++) {
			firstPart.add(this.getCarte(i));
		}
		this.paquet = new ArrayList<>();
		for(Carte carte : secondPart) {
			this.paquet.add(carte);
		}
		for(Carte carte : firstPart) {
			this.paquet.add(carte);
		}
	}
	
	
	
}
