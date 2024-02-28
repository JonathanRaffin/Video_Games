package GUI;

import Controller.ControllerGUI;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
		
	protected GUI GUI;
	protected Container contentPane;
	protected JPanel buttons, blankarea;
	public JPanel btnPlay, btnRules, btnQuit;
	protected JLabel title, image;

	/**
	 * Construit l'interface du Menu.
	 * @param GUI
	 */
	public Menu (GUI GUI) {
        this.GUI = GUI; 
        super.setBackground(Systeme.getBackgroundColor());
        super.setPreferredSize(new Dimension(1200,800));
        super.setLayout(new BorderLayout());
      
        this.title = new JLabel();
        this.buttons = new JPanel(); 
        this.blankarea = Systeme.blankArea(new Dimension(500,500));
        this.btnPlay = new JPanel();
        this.btnRules = new JPanel(); 
        this.btnQuit = new JPanel();
        this.image = new JLabel();
        
        this.setFrame();
        super.add(this.title, BorderLayout.NORTH);
        super.add(this.buttons, BorderLayout.CENTER);
        super.add(this.blankarea, BorderLayout.WEST);
        super.add(this.image, BorderLayout.EAST);
    }
	
	/**
	 * Met à jour les différents composants de la fenêtre.
	 */
	public void setFrame() {
		Systeme.initPictures(this.title, "pictures/title.png", new Dimension(1200, 300), true);
		
		this.buttons.setBackground(Systeme.getBackgroundColor());
		this.buttons.setLayout(new GridBagLayout());
		GridBagConstraints gbc = Systeme.getGBC(20);
        
        Systeme.initButtonControl(this.GUI, this.btnPlay, "Jouer");
        Systeme.initButtonControl(this.GUI, this.btnRules, "Règles");
        Systeme.initButtonControl(this.GUI, this.btnQuit, "Quitter");
        
        this.buttons.add(this.btnPlay, gbc);
		this.buttons.add(this.btnRules, gbc);
		this.buttons.add(this.btnQuit, gbc);
		
        Systeme.initPictures(this.image, "pictures/Logo.png", new Dimension(500, 500), true);
	}
	
}
