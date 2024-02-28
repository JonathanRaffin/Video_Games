package gui;

import java.awt.*;
import javax.swing.*;

public class Systeme {
	
	public Systeme() {}
	
    /**
     * Créer un JPanel,
     * avec des dimensions prédéfinies.
     */
	public static JPanel initJPanel(int x, int y) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(x, y));
        return panel;
    }

    /**
     * Créer un JLabel,
     * avec des paramètres prédéfinies.
     */
    public static JLabel initJLabel(String texte, Color color, boolean center, int size){
        JLabel label = new JLabel(texte);
        label.setForeground(color);
        if(center)
            label.setHorizontalAlignment(JLabel.CENTER);
        if(size != 0)
            label.setFont(new Font("Serif", Font.BOLD, size));
        return label;
    }

    /**
     * Créer un JLabel,
     * avec des paramètres prédéfinies.
     */
    public static JLabel initJLabel(String texte, boolean center){
        return Systeme.initJLabel(texte, Color.black, center, 0);
    }

}