package GUI;

import java.awt.*;
import javax.swing.*;

import blackjack.Personne;

public class Info extends JPanel{
	
	protected GUI GUI;
	protected Personne personne;
	protected JPanel buton, blackjackOrNot, areaCenter, btnPlayAgain;
	
	/**
	 * Construit l'interface pour afficher les résultats.
	 * @param GUI
	 * @param personne: vainqueur de la manche.
	 */
	public Info(GUI GUI, Personne personne) {
		super.setBackground(Systeme.getBackgroundColor());
        super.setPreferredSize(new Dimension(1200,800));
        super.setLayout(new BorderLayout());
        
        this.GUI = GUI;
        this.personne = personne;
        
        this.buton = new JPanel();
        this.blackjackOrNot = new JPanel();
        this.areaCenter = new JPanel();
        this.btnPlayAgain = new JPanel();
        
        this.setFrame();
       
        super.add(this.blackjackOrNot, BorderLayout.NORTH);
        super.add(this.areaCenter, BorderLayout.CENTER);
        super.add(this.buton, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Met à jour les différents composants de la fenêtre.
	 */
	public void setFrame() {
		GridBagConstraints gbc = Systeme.getGBC(30);
		
		JLabel winner = new JLabel();
		if(this.personne != null) {
			Systeme.initLabel(winner, "Le " + this.personne.getName() + " a gagné !", 60);			
		} else {
			Systeme.initLabel(winner, "Egalité !", 60);
		}

		JPanel tmp = new JPanel();
		tmp.setLayout(new GridBagLayout());
		tmp.setBackground(Systeme.getBackgroundColor());
		tmp.add(winner, gbc);
		this.blackjackOrNot.setBackground(Systeme.getBackgroundColor());
		this.blackjackOrNot.add(tmp);
		
		this.areaCenter.setLayout(new GridBagLayout());
		this.areaCenter.setBackground(Systeme.getBackgroundColor());
        
        JLabel result = new JLabel();
        if(this.personne == this.GUI.blackjack.getCroupier()) {
        	Systeme.initLabel(result, "Vous avez perdu.");        	
        } else if(this.personne == this.GUI.blackjack.getPlayer1()) {
        	Systeme.initLabel(result, "Vous avez gagné " + this.GUI.blackjack.getPlayer1().getMise() + " sur ce tour.");        	
        } else {
        	Systeme.initLabel(result, "Vous récupéré votre mise: " + this.GUI.blackjack.getPlayer1().getMise() + ".");
        }
        
        int valueCroupier = this.GUI.blackjack.valeurPaquet(this.GUI.blackjack.getCroupier().getHand());
        int valuePlayer = this.GUI.blackjack.valeurPaquet(this.GUI.blackjack.getPlayer1().getHand());
        
        JLabel action = new JLabel();
        if(this.personne == this.GUI.blackjack.getCroupier()) {
        	if(this.personne.isBlackjack()) {
        		Systeme.initLabel(action, "Le Croupier a fait un BLACKJACK !");        	        		
        	} else if(this.personne.isHasWin()) {
        		if(valueCroupier > 21) {
        			Systeme.initLabel(action, "Le Croupier a un score qui dépasse 21.");
        		} else if(valuePlayer > 21) {
        			Systeme.initLabel(action, "Vous avez un score qui dépasse 21.");
        		} else {
        			Systeme.initLabel(action, "Le Croupier a un score plus élevé que vous.");
        		}
        	}
        } else if(this.personne == this.GUI.blackjack.getPlayer1()) {
        	if(this.personne.isBlackjack()) {
        		Systeme.initLabel(action, "Vous avez fait un BLACKJACK !");        	        		
        	} else if(this.personne.isHasWin()) {
        		if(valuePlayer > 21) {
        			Systeme.initLabel(action, "Vous avez un score qui dépasse 21.");
        		} else if(valueCroupier > 21){
        			Systeme.initLabel(action, "Le Croupier a un score qui dépasse 21.");
        		} else {
        			Systeme.initLabel(action, "Vous avez un score plus élevé que le Croupier.");
        		}
        	}
        } else {
        	Systeme.initLabel(action, "Vous avez dépassé 21, comme le Croupier");        		
        }
        
        JLabel score = new JLabel();
        Systeme.initLabel(score, "Score: Croupier: " + valueCroupier + ", " + this.GUI.blackjack.getPlayer1().getName() + ": " + valuePlayer + ".");        	
        
        JLabel solde = new JLabel();
        Systeme.initLabel(solde, "Votre solde est désormais de " + this.GUI.blackjack.getPlayer1().getMoney() + ".");
       
        this.areaCenter.add(result, gbc);
        this.areaCenter.add(action, gbc);
        this.areaCenter.add(score, gbc);
        this.areaCenter.add(solde, gbc);
		
		
        if(this.GUI.blackjack.getPlayer1().getMoney() == 0) {
        	Systeme.initButtonControl(this.GUI, this.btnPlayAgain, "Menu ");
        } else {
        	Systeme.initButtonControl(this.GUI, this.btnPlayAgain, "Rejouer");        	
        }
		this.buton.setLayout(new BorderLayout());
		this.buton.add(Systeme.blankArea(new Dimension((int)512.5, 75)), BorderLayout.WEST);
		this.buton.add(this.btnPlayAgain, BorderLayout.CENTER);
		this.buton.add(Systeme.blankArea(new Dimension((int)512.5, 75)), BorderLayout.EAST);
		this.buton.add(Systeme.blankArea(new Dimension(1200, (int)37.5)), BorderLayout.NORTH);
		this.buton.add(Systeme.blankArea(new Dimension(1200, (int)37.5)), BorderLayout.SOUTH);
	}
	
	

}
