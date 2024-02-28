package GUI;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class Cross extends JPanel{
  
    private int length = 9;
    private Color color;

    /**
     * Create a cross.
     * @param color : Colour of the cross.
     */
    public Cross (Color color) {
        this.color = color;
    }

    /**
     * Draw the cross in a JPanel.
     */
    public void paint (Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.white);
        g.fillRect(0,  0,  length,  length);
        g.setColor(this.color);
        g.setStroke(new BasicStroke(1.5f));
        g.drawLine(length, 0, 0, length);
        g.drawLine(0, 0, length, length);
    }
}
