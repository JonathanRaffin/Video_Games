package GUI;

import java.awt.Color;
import GUI.control.*;

public class GUI {
    
    public static final Color[] colors = {
        Color.blue, Color.green, Color.red,
        Color.cyan, Color.magenta, Color.orange, 
        Color.pink, Color.gray, Color.yellow
    };

    private Frame screen;

    /**
     * Create the graphical interface and add the controllers.
     * @param size : Number of cores. 
     */
    public GUI (int size) {
        this.screen =  new Frame(size);
        this.screen.addKeyListener(new KeyboardControl(this.screen));
        this.screen.areaTime.addMouseListener(new MouseControl(this.screen));
    }

    /**
     * @return the value of the speed.
     */
    public int getSpeed () {
        return this.screen.areaTime.getSpeed();
    }

    /**
     * Changes the background colour of a core depending on the player.
     * @param position : Position of the core.
     * @param player : One player (to recover his colour).
     */
    public void setPanelColor (int position, int player) {
        this.screen.areaGame.setPanelColor(position, player);
    }

    /**
     * Changes the background colour of a core.
     * @param position : Position of the core.
     * @param color : Colour to put in the background of the core.
     */
    public void setPanelColor (int position, Color color) {
        this.screen.areaGame.setPanelColor(position, color);
    }
}