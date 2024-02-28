package gui;

// import hex.*;
import control.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class Menu extends JFrame implements ActionListener, ChangeListener{

    protected Container contentPane;
    protected JPanel center;
    protected JLabel idTaille;
    protected JSlider sliderTaille;
    protected JButton newGame, botGame, mctsGame;
    protected int taille;
    protected int bot;

    public Menu() {
        /* Initialisation de la fenêtre */
		super("Hex");
        super.setMinimumSize(new Dimension(300, 300));
        this.contentPane = super.getContentPane();
        this.contentPane.setLayout(new BorderLayout());
        this.bot = 0;


        /* Ajout des différents composants */
        this.taille = 6;
        this.initParam();
        this.initMarge();
		
		/* Affichage de la fenêtre */
        super.pack();
        super.setVisible(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialise les composants,
     * Ce sont les parametres de la partie.
     */
    public void initParam() {
        this.center = new JPanel();
        this.center.setLayout(new GridLayout(4, 0));
        this.center.add(Systeme.initJLabel("Jeu de Hex !", Color.red, true, 20));
        
        this.sliderTaille = new JSlider(6, 22, this.taille);
        this.sliderTaille.setMinorTickSpacing(1);  
        this.sliderTaille.setMajorTickSpacing(4);  
        this.sliderTaille.setPaintTicks(true);  
        this.sliderTaille.setPaintLabels(true);
        this.sliderTaille.addChangeListener(this);
        
        this.idTaille = new JLabel("Taille: " + Integer.toString(this.sliderTaille.getValue()));
        this.idTaille.setHorizontalAlignment(JLabel.CENTER);
        
        this.center.add(this.idTaille);
        this.center.add(this.sliderTaille);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        this.newGame = new JButton("Nouvelle partie");
        this.newGame.addActionListener(this);
        buttons.add(this.newGame);

        this.botGame = new JButton("Bot");
        this.botGame.addActionListener(this);
        buttons.add(this.botGame);

        this.mctsGame = new JButton("MCTS");
        this.mctsGame.addActionListener(this);
        buttons.add(this.mctsGame);
        
        this.center.add(buttons);
        this.contentPane.add(center, BorderLayout.CENTER);
    }

    /**
     * Ajoute des marges, pour l'affichage.
     */
    public void initMarge(){
        this.contentPane.add(Systeme.initJPanel(300, 50), BorderLayout.NORTH);
        this.contentPane.add(Systeme.initJPanel(50, 200), BorderLayout.EAST);
        this.contentPane.add(Systeme.initJPanel(50, 200), BorderLayout.WEST);
        this.contentPane.add(Systeme.initJPanel(300, 50), BorderLayout.SOUTH);
    }

    /**
     * Action du boutton "Nouvelle Partie", pour lancer une nouvelle partie.
     * @param e : event lors du clic (Système java).
     */
    @Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.botGame)           this.bot = 1;
        else if (e.getSource() == this.mctsGame)    this.bot = 2;
        else                                        this.bot = 0;
        new Execution(this, this.taille, this.bot);
	}
    
    /**
     * Met à jour le texte, enfonction de la valeur sélectionné.
     * @param event
     */
    @Override
    public void stateChanged(ChangeEvent event) {
        this.taille = ((JSlider)event.getSource()).getValue();
        this.idTaille.setText("Taille: " + this.taille);
    }

}
