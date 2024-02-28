package GUI;

import Controller.ControllerGUI;
import javax.swing.*;
import java.awt.*;

public class Rules extends JPanel{

	protected GUI GUI;
	public String index;
	protected JPanel buton, btnBack;
	protected JLabel rules;
	
	/**
	 * Construit l'interface pour afficher les règles.
	 * @param GUI
	 * @param index
	 */
	public Rules (GUI GUI, String index) {
		super.setBackground(Systeme.getBackgroundColor());
		super.setPreferredSize(new Dimension(1200,800));
		super.setLayout(new BorderLayout());
		
		this.GUI = GUI;
		this.index = index;
		this.buton = new JPanel();
		this.btnBack = new JPanel();
		this.rules = new JLabel();
		
		this.SetFrame();

		super.add(this.rules, BorderLayout.CENTER);
		super.add(this.buton, BorderLayout.SOUTH);
		
    }
	
	/**
	 * Met à jour les différents composants de la fenêtre.
	 */
	public void SetFrame() {
		Systeme.initPictures(this.rules, "pictures/rules.png", new Dimension(1200, 650));
		
		Systeme.initButtonControl(this.GUI, this.btnBack, this.index);
		
		this.buton.setLayout(new BorderLayout());
		this.buton.add(Systeme.blankArea(new Dimension((int)512.5, 75)), BorderLayout.WEST);
		this.buton.add(this.btnBack, BorderLayout.CENTER);
		this.buton.add(Systeme.blankArea(new Dimension((int)512.5, 75)), BorderLayout.EAST);
		this.buton.add(Systeme.blankArea(new Dimension(1200, (int)37.5)), BorderLayout.NORTH);
		this.buton.add(Systeme.blankArea(new Dimension(1200, (int)37.5)), BorderLayout.SOUTH);
	}
    
}
