package GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    private int width = 1920;
    private int height = 1080;
    private Dimension sizeFrame = new Dimension(width, height);

    private Container contentPane;
    private JPanel panelL, panelC, panelR;
    
    protected AreaGame areaGame;
    protected AreaTime areaTime;

    /**
     * Builds the entire GUI.
     * @param size : Number of cores.
     */
    public Frame (int size) {
        super("CoreWar");
        super.setPreferredSize(sizeFrame);
        super.setIconImage(Toolkit.getDefaultToolkit().getImage("images/CoreWarLogo.png"));

        this.contentPane = getContentPane();
        this.contentPane.setLayout(new BorderLayout());

        this.panelL = new JPanel();
        this.panelR = new JPanel();
        this.panelC = new JPanel();

        this.areaGame = new AreaGame(size);
        this.areaTime = new AreaTime();

        this.initializeArea();

        super.pack();
        super.setVisible(true);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Initializes areas such as margins and the play area (AreaGame).
     */
    private void initializeArea () {
        this.panelR.setBackground(Color.GRAY);
        this.panelR.add(this.areaTime);
        this.contentPane.add(this.panelR, BorderLayout.EAST);

        this.panelL.setBackground(Color.GRAY);
        this.panelL.setPreferredSize(new Dimension(125, height));
        this.contentPane.add(this.panelL, BorderLayout.WEST);

        this.panelC.setBackground(Color.BLACK);
        this.panelC.add(this.areaGame);
        this.contentPane.add(this.panelC, BorderLayout.CENTER);
    }

    /**
     * Increase the speed of the game display.
     */
    public void increaseSpeed () {
        this.areaTime.increaseSpeed();
    }

    /**
     * Decreases the speed of the game display.
     */
    public void decreaseSpeed () {
        this.areaTime.decreaseSpeed();
    }

    /**
     * Get the '+' image for controller management.
     * @return : image stored in a JLabel.
     */
    public JLabel getIconMore () {
        return this.areaTime.getIconMore();
    }

    /**
     * Get the '-' image for controller management.
     * @return : image stored in a JLabel.
     */
    public JLabel getIconLess () {
        return this.areaTime.getIconLess();
    }
}