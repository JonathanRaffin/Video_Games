package cartes;

public class Carte {

	protected String value;
	protected String color;
	
	/**
	 * Créer un carte.
	 * @param value : valeur de la carte.
	 * @param color : couleur de a carte.
	 */
	public Carte(String value, String color) {
		this.value = value;
		this.color = color;
	}
	
	/** 
	 * @return la valeur de la carte.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Change la valeur de la carte.
	 * @param value : nouvelle valeur.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/** 
	 * @return la couleur de la carte.
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Change la couleur de la carte.
	 * @param color : nouvelle couleur.
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Affiche une carte, avec sa valeur et sa couleur.
	 */
	@Override
	public String toString() {
		return "Carte [value=" + value + ", color=" + color + "]";
	}
	
	
	
}
