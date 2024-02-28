package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Piece extends JPanel {
    private JLabel display = new JLabel();

    /**
     * Create a game piece with a JLabel containing either a number or an image.
     */
    public Piece () {
        super.add(display, JLabel.CENTER);
    }

    /**
     * Create an empty game piece.
     */
    public void setEmpty () {
        this.clear();
        super.setBackground(Color.black);
    }

    /**
     * Create a game piece with a number.
     * @param number : Number inscribed on the piece.
     */
    public void set (String number) {
        this.clear();
        this.display.setText(number);
    }

    /**
     * Create a game piece with an image.
     * @param image : Image displayed on the piece.
     */
    public void set (BufferedImage image) {
        this.clear();
        this.display.setIcon(new ImageIcon((Image)image));
    }

    /**
     * Clean up the game.
     */
    private void clear () {
        this.display.setIcon(null);
        this.display.setText(" ");
        super.setBackground(Color.gray);
    }
}
