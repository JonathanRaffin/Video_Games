package GUI;

import java.awt.*;
import javax.swing.*;
import Controller.*;

public class Bet extends JPanel{
	
	protected GUI GUI;
	protected JPanel butons, tokens;
	public JPanel informations, btnContinue, btnBack, btnReset, btnAllIn;
	protected JLabel title, namePlayer, soldePlayer, betPlayer;
	public JLabel token1, token5, token25, token50, token100, token500, token1000;
	
	/**
	 * Construit l'interface pour que le joueur mise.
	 * @param GUI
	 */
	public Bet (GUI GUI) {
		super.setBackground(Systeme.getBackgroundColor());
		super.setPreferredSize(new Dimension(1200,800));
		super.setLayout(new BorderLayout());
		
		this.GUI = GUI;
		this.title = new JLabel(); this.informations = new JPanel();
		this.butons = new JPanel(); this.tokens = new JPanel();
		this.btnContinue = new JPanel(); this.btnBack = new JPanel();
		this.btnReset = new JPanel(); this.btnAllIn = new JPanel();
		
		this.token1 = new JLabel(); this.token5 = new JLabel();
		this.token25 = new JLabel(); this.token50 = new JLabel();
		this.token100 = new JLabel(); this.token500 = new JLabel();
		this.token1000 = new JLabel();
		
		this.namePlayer = new JLabel(); this.soldePlayer = new JLabel(); this.betPlayer = new JLabel();
		
		this.SetFrame();
		
		super.add(this.title, BorderLayout.NORTH);
		super.add(this.informations, BorderLayout.CENTER);
		super.add(this.butons, BorderLayout.EAST);
		super.add(this.tokens, BorderLayout.SOUTH);
		
    }
	
	/**
	 * Met à jour les différents composants de la fenêtre.
	 */
	public void SetFrame() {
		Systeme.initPictures(this.title, "pictures/BetTitle.png", new Dimension(1200,150), true);
		
		this.setInformations();
		
		GridBagConstraints gbc = Systeme.getGBC(10);

		this.butons.setPreferredSize(new Dimension(350, 450));
		this.butons.setBackground(Systeme.getBackgroundColor());
		this.butons.setLayout(new GridBagLayout());
		Systeme.initButtonControl(this.GUI, this.btnContinue, "Continuer");
		Systeme.initButtonControl(this.GUI, this.btnReset, "Reset");
		Systeme.initButtonControl(this.GUI, this.btnBack, "Annuler");
		Systeme.initButtonControl(this.GUI, this.btnAllIn, "All In");
		this.butons.add(this.btnContinue, gbc);
		this.butons.add(this.btnReset, gbc);
		this.butons.add(this.btnAllIn, gbc);
		this.butons.add(this.btnBack, gbc);
		
		this.setAreaTokens();
	}
	
	/**
	 * Met à jour les différentes informations du joueur.
	 */
	public void setInformations() {
		this.informations.setBackground(Systeme.getBackgroundColor());
		this.informations.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(20, 0, 20, 0);
		Systeme.initLabel(this.namePlayer, "Joueur: " + this.GUI.blackjack.getPlayer1().getName());
		Systeme.initLabel(this.soldePlayer, "Votre solde: " + this.GUI.blackjack.getPlayer1().getMoney());
		Systeme.initLabel(this.betPlayer, "Votre mise: " + this.GUI.blackjack.getPlayer1().getMise());
		this.informations.add(this.namePlayer, gbc);
		this.informations.add(this.soldePlayer, gbc);
		this.informations.add(this.betPlayer, gbc);
	}
	
	/**
	 * Créer la partie du bas avec tous les jetosn pour que l'utilisateur puissent miser.
	 */
	public void setAreaTokens() {
		this.tokens.setBackground(Systeme.getBackgroundColor());
		this.tokens.setPreferredSize(new Dimension(200,200));
		this.tokens.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		if(this.GUI.blackjack.getPlayer1().getMoney() >= 1) {
			Systeme.initPictures(this.token1, "pictures/jeton/Jeton1.png", new Dimension(150,150));
			this.token1.addMouseListener(new ControllerBet(this.GUI, this.token1, "Jeton 1"));
			
			if(this.GUI.blackjack.getPlayer1().getMoney() >= 5) {
				Systeme.initPictures(this.token5, "pictures/jeton/Jeton5.png", new Dimension(150,150));
				this.token5.addMouseListener(new ControllerBet(this.GUI, this.token5, "Jeton 5"));
				
				if(this.GUI.blackjack.getPlayer1().getMoney() >= 25) {
					Systeme.initPictures(this.token25, "pictures/jeton/Jeton25.png", new Dimension(150,150));
					this.token25.addMouseListener(new ControllerBet(this.GUI, this.token25, "Jeton 25"));
					
					if(this.GUI.blackjack.getPlayer1().getMoney() >= 50) {
						Systeme.initPictures(this.token50, "pictures/jeton/Jeton50.png", new Dimension(150,150));
						this.token50.addMouseListener(new ControllerBet(this.GUI, this.token50, "Jeton 50"));
						
						if(this.GUI.blackjack.getPlayer1().getMoney() >= 100) {
							Systeme.initPictures(this.token100, "pictures/jeton/Jeton100.png", new Dimension(150,150));
							this.token100.addMouseListener(new ControllerBet(this.GUI, this.token100, "Jeton 100"));
							
							if(this.GUI.blackjack.getPlayer1().getMoney() >= 500) {
								Systeme.initPictures(this.token500, "pictures/jeton/Jeton500.png", new Dimension(150,150));
								this.token500.addMouseListener(new ControllerBet(this.GUI, this.token500, "Jeton 500"));
								
								if(this.GUI.blackjack.getPlayer1().getMoney() >= 1000) {
									Systeme.initPictures(this.token1000, "pictures/jeton/Jeton1000.png", new Dimension(150,150));	
									this.token1000.addMouseListener(new ControllerBet(this.GUI, this.token1000, "Jeton 1000"));
									
								}
							}
						}
					}
				}
			}
		}
		this.tokens.add(this.token1);
		this.tokens.add(this.token5);
		this.tokens.add(this.token25);
		this.tokens.add(this.token50);
		this.tokens.add(this.token100);
		this.tokens.add(this.token500);
		this.tokens.add(this.token1000);
	}
    
}