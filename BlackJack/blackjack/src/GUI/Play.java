package GUI;

import java.awt.*;
import javax.swing.*;
import view.*;

public class Play extends JPanel{
	
	protected GUI GUI;
	protected JLabel betActual;
	protected JPanel infoBet, cardPlayer, cardDealer, areaButtonsLeft, areaButtonsRight;
	public JPanel btnPiocher, btnRester;
	
	/**
	 * Construit l'interface du jeu.
	 * @param GUI
	 */
	public Play(GUI GUI) {
		this.GUI = GUI;
		super.setBackground(Color.gray);
        super.setPreferredSize(new Dimension(1200,800));
        super.setLayout(new BorderLayout());
        
        this.betActual = new JLabel(); this.infoBet = new JPanel();
        this.cardPlayer = new JPanel(); this.cardDealer = new JPanel();
        this.areaButtonsLeft = new JPanel(); this.areaButtonsRight = new JPanel();
        
        this.btnPiocher = new JPanel(); this.btnRester = new JPanel(); 
        
        this.setFrame();

        super.add(this.cardDealer, BorderLayout.NORTH);
        super.add(this.areaButtonsRight, BorderLayout.WEST);
        super.add(this.infoBet, BorderLayout.CENTER);
        super.add(this.areaButtonsLeft, BorderLayout.EAST);
        super.add(this.cardPlayer, BorderLayout.SOUTH);
	}
	
	/**
	 * Met à jour les différents composants de la fenêtre.
	 */
	public void setFrame() {
		Systeme.initLabel(this.betActual, "Mise en jeu: " + this.GUI.blackjack.getPlayer1().getMise());
		this.infoBet.setLayout(new GridBagLayout());
		this.infoBet.setBackground(Systeme.getBackgroundColor());
		this.infoBet.add(this.betActual, Systeme.getGBC(30));
		
		this.setAreaDealer();
		this.setAreaPlayer();
		
		Systeme.initButtonControl(this.GUI, this.btnPiocher, "Piocher");
        Systeme.initButtonControl(this.GUI, this.btnRester, "Rester");
        
        GridBagConstraints gbc = Systeme.getGBC(10);
        this.areaButtonsLeft.setLayout(new GridBagLayout());
        this.areaButtonsLeft.setBackground(Systeme.getBackgroundColor());
        this.areaButtonsLeft.setPreferredSize(new Dimension(300,300));
        this.areaButtonsLeft.add(this.btnRester, gbc);
        this.areaButtonsRight.setLayout(new GridBagLayout());
        this.areaButtonsRight.setBackground(Systeme.getBackgroundColor());
        this.areaButtonsRight.setPreferredSize(new Dimension(300,300));
        this.areaButtonsRight.add(this.btnPiocher, gbc);
	}
	
	/**
	 * Met à jour la zone du croupier.
	 */
	public void setAreaDealer() {
		this.cardDealer.setPreferredSize(new Dimension(1200,300));
		this.cardDealer.setLayout(new BorderLayout());
		
		JPanel nbPoint = new JPanel();
		Systeme.initLabelNbPoint(nbPoint, "Croupier", this.GUI.blackjack.valeurLastCardCroupier());
		
		JPanel tmpCenter = new JPanel();
		tmpCenter.setBackground(Systeme.getBackgroundColor());
		tmpCenter.add(new VueCarte());
		tmpCenter.add(new VueCarte(this.GUI.blackjack.getCroupier().getHand().getCarte(1)));
		
		this.cardDealer.add(Systeme.blankArea(new Dimension(1200,50)), BorderLayout.NORTH);
		this.cardDealer.add(nbPoint, BorderLayout.WEST);
		this.cardDealer.add(tmpCenter, BorderLayout.CENTER);
		this.cardDealer.add(Systeme.blankArea(new Dimension(1200,50)), BorderLayout.SOUTH);
	}
	
	/**
	 * Met à jour la zone du joueur.
	 */
	public void setAreaPlayer() {
        VuePaquetVisible viewPlayer = new VuePaquetVisible(this.GUI.blackjack.getPlayer1().getHand());
        this.cardPlayer.setPreferredSize(new Dimension(1200,300));
		this.cardPlayer.setBackground(Systeme.getBackgroundColor());
		this.cardPlayer.setLayout(new BorderLayout());
		
		JPanel nbPoint = new JPanel();
		Systeme.initLabelNbPoint(nbPoint, this.GUI.blackjack.getPlayer1().getName(), this.GUI.blackjack.valeurPaquet(this.GUI.blackjack.getPlayer1().getHand()));
		
		JPanel tmpCenter = new JPanel();
		tmpCenter.setBackground(Systeme.getBackgroundColor());
		tmpCenter.add(viewPlayer);
		
		this.cardPlayer.add(Systeme.blankArea(new Dimension(1200,50)), BorderLayout.NORTH);
		this.cardPlayer.add(nbPoint, BorderLayout.WEST);
		this.cardPlayer.add(tmpCenter, BorderLayout.CENTER);
		this.cardPlayer.add(Systeme.blankArea(new Dimension(1200,50)), BorderLayout.SOUTH);
	}

}
