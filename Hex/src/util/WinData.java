package util;
/**
 * Classe étant associée à une paire et permettant de calculer son taux de réussite/défaite
 *
 */
public class WinData {
	/**
	 * le nombre de fois qu'une case est essayée
	 */
	private int nbEssais;
	/**
	 * le nombre de fois qu'une case remporte une partie
	 */
	private int nbSucces;
	/**
	 * le nombre de fois qu'une case perd une partie
	 */
	private int nbDefaites;
	
	/************************************************************/
	/*********************** CONSTRUCTEUR ***********************/
	/************************************************************/
	
	/**
	 * Permet de créer une instance de WinData
	 */
	public WinData(){
		this.nbEssais = 0;
		this.nbSucces = 0;
		this.nbDefaites = 0;
	}
	
	/************************************************************/
	/************************* SETTERS **************************/
	/************************************************************/
	
	/**
	 * Ajouter un essai à la case
	 */
	public void addEssai() {
		this.nbEssais+=1;
	}
	
	/**
	 * ajoute une victoire à la case
	 */
	public void addSucce() {
		this.nbEssais+=1;
		this.nbSucces+=1;
	}
	
	/**
	 * ajoute une defaite à la case
	 */
	public void addDefaite() {
		this.nbEssais+=1;
		this.nbDefaites+=1;
	}
	
	/************************************************************/
	/************************* MÉTHODES *************************/
	/************************************************************/
	
	/**
	 * 
	 * @return le taux de succès d'une case
	 */
	public float getValue() {
		return ((float)nbSucces/nbEssais)-((float)nbDefaites/nbEssais);
	}

	
	/**
	 * Permet de mieux visusaliser la classe en terminal
	 */
	@Override
	public String toString() {
		return "WinData [nbEssais=" + nbEssais + ", nbSucces=" + nbSucces + ", nbDefaites=" + nbDefaites + ", Ratio =" + getValue() + "]";
	}

}
