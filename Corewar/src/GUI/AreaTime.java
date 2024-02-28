package GUI;

import javax.swing.*;
import java.awt.*;

public class AreaTime extends JPanel{

    private int[] speeds = {200, 100, 67, 50, 40, 33, 29, 25, 22, 20, 17, 15, 13, 10, 7, 4, 1};
    private int speedIndex = 4;

    private JLabel iconMore;
    private JLabel iconLess;

    /**
     * Builds the right side of the game with 2 buttons to manage time.
     */
    public AreaTime () {
        super.setBackground(Color.BLACK);
        this.iconMore = new JLabel(new ImageIcon("images/More.png"));
        this.iconLess = new JLabel(new ImageIcon("images/Less.png"));
        this.init();
    }

    /**
     * @return the value of the speed.
     */
    public int getSpeed () {
        return this.speeds[this.speedIndex];
    }

    /**
     * Initializes the area of the 2 buttons.
     */
    private void init () {
        super.setLayout(new FlowLayout());
        super.add(this.iconMore);
        super.add(this.iconLess);
    }

    /**
     * Increases the speed of the game.
     */
    public void increaseSpeed () {
        this.speedIndex = Math.min(this.speedIndex+1, this.speeds.length-1);
    }

    /**
     * Decreases the speed of the game.
     */
    public void decreaseSpeed () {
        this.speedIndex = Math.max(this.speedIndex-1, 0);
    }

    /**
     * Get the '+' image for controller management.
     * @return : image stored in a JLabel.
     */
    public JLabel getIconMore () {
        return this.iconMore;
    }

    /**
     * Get the '-' image for controller management.
     * @return : image stored in a JLabel.
     */
    public JLabel getIconLess () {
        return this.iconLess;
    }
}