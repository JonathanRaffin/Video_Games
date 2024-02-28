package GUI;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class AreaGame extends JPanel{

    public static final Color[] colors = {
        Color.blue, Color.green, Color.red,
        Color.cyan, Color.magenta, Color.orange, 
        Color.pink, Color.gray, Color.yellow
    };

    private int size;
    private ArrayList<JPanel> array;

    /**
     * Builds the playing area.
     * @param size : Number of cores.
     */
    public AreaGame (int size) {
        this.size = size;
        super.setBackground(Color.BLACK);
        this.createListPanel();
        this.displayPanel();
    }

    /**
     * Builds the game area with a base value of 8000.
     */
    public AreaGame () {
        this(8000);
    }

    /**
     * Create a list of JPanels. Each JPanel represents a core.
     */
    private void createListPanel () {
        ArrayList<JPanel> list = new ArrayList<>();
        for (int i=0; i<this.size; i++){
            JPanel square = new JPanel();
            square.setPreferredSize(new Dimension(9, 9));
            square.setLayout(new BorderLayout());
            list.add(square);
        }
        this.array = list;
    }

    /**
     * Displays the list of cores in a grid.
     */
    private void displayPanel () {
        double root = Math.sqrt(this.size);
        int side = (int) Math.ceil(root);
        setLayout(new GridLayout(side, side, 2, 2));
        for(JPanel square : this.array){
            add(square);
        }
    }

    /**
     * Changes the background colour of a core.
     * @param position : Position of the core.
     * @param color : Colour to put in the background of the core.
     */
    public void setPanelColor (int position, Color color) {
        JPanel panelSelected = this.array.get(position);
        panelSelected.setBackground(color);
    }

    /**
     * Changes the background colour of a core depending on the player.
     * @param position : Position of the core.
     * @param player : One player (to recover his colour).
     */
    public void setPanelColor (int position, int player) {
        this.setPanelColor(position, AreaGame.colors[player%9]);
    }

    /**
     * Adds a cross design to a JPanel.
     * @param position : Position of the JPanel where the cross will be drawn.
     * @param color : Colour of the cross.
     */
    public void setPanelInCross (int position, Color color) {
        JPanel panelSelected = this.array.get(position);
        panelSelected.add(new Cross(color), BorderLayout.CENTER);
    }
}