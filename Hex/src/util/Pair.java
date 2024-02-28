package util;
/**
 * Classe permettant de représenter une case de hex.
 */
public class Pair {
	/**
	 * valeur de x et de y
	 */
	int x;
	int y;
	
	/************************************************************/
	/*********************** CONSTRUCTEUR ***********************/
	/************************************************************/

	/**
	 * 
	 * @param x : ligne de la paire
	 * @param y : colonne de la paire
	 */
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/************************************************************/
	/************************** GETTERS *************************/
	/************************************************************/
	
	/**
	 * 
	 * @return x
	 */
	public int getP1() {
		return x;
	}

	/**
	 * 
	 * @return y
	 */
	public int getP2() {
		return y;
	}


	/**
	 * Permet de visualiser avec un toString() la paire en question
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}


	/**
	 * Permet de tester l'équité de deux paires
	 */
	@Override
	public boolean equals(Object obj) {
		return ((Pair) obj).getP1()==x && ((Pair) obj).getP2()==y;
	}

	public void setP1(int i) {
		this.x = i;
	}
	
	public void setP2(int i) {
		this.y = i;
	}
	


}
