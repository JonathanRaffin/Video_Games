package blackjack;

import GUI.*;

/**
 * Classe principale du programme.
 */
public class MainClass {

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		new GUI(blackjack);
	}
	
}
