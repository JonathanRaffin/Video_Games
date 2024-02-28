package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import image.Image;
import controller.MouseControl;

public class Grid extends JPanel {
    
    protected Piece[] grid;
    private int width;
    private int height;
    private BufferedImage[] list;
    private boolean images;
    private int numImage;

    /**
     * Create the play area.
     * @param width : Number of lines in the game.
     * @param height : Number of columns in the game.
     * @param game : Game model.
     * @param images : Boolean that indicates whether to use images in the GUI.
     */
    public Grid (int width, int height, game.Taquin game, boolean images) {
        this.width = width;
        this.height = height;
        
        this.images = images;

        this.numImage = Math.abs(new Random().nextInt() % 2);
        
        this.grid = new Piece[height * width];
        this.initGame(game);
        this.list = new Image(this.numImage).cutImage(width, height);
        
        super.setPreferredSize(new Dimension(this.list[0].getWidth() * width, this.list[0].getHeight() * height));
    }

    /**
     * Create the list of parts according to the model and place them in a grid.
     * And mouse control added.
     * @param game : Game model.
     */
    public void initGame (game.Taquin game) {
        super.setBackground(Color.blue);
        super.setLayout(new GridLayout(this.height, this.width, 2, 2));
        for (int i = 0; i < this.width * this.height; i++) {
            this.grid[i] = new Piece();
            this.grid[i].addMouseListener(new MouseControl(game, this, this.images));
            super.add(this.grid[i]);
        } 
    } 

    /**
     * Displays all but one piece for play.
     * @param game : Game model.
     * @param images : Boolean that indicates whether to use images in the GUI.
     */
    public void displayGame (int[] game, boolean images) {
        for (int i=0; i<game.length; i++) {
            if (game[i] == this.width * this.height) this.grid[i].setEmpty();
            else {
                if (images) this.grid[i].set(list[game[i] - 1]);
                else this.grid[i].set(String.valueOf(game[i]));
            } 
        }
    }

    /**
     * Displays the values or the whole image at the end of the game.
     * @param game : Game model.
     * @param images : allows to know if the whole image is displayed. 
     */
    public void displayEndGame (game.Taquin game, boolean images) {
        if (!images) {
            for (int i=0; i<this.width*this.height; i++)
                this.grid[i].set(String.valueOf(i+1));
        } 
        else {
            super.removeAll();
            super.setLayout(new BorderLayout());
            super.revalidate();
            Piece piece = new Piece();
            piece.set(new Image(this.numImage).getImage());
            piece.addMouseListener(new MouseControl(game, this, this.images));
            super.add(piece);
        }
    }

    /**
     * Removes the entire image display to have a "neutral" area.
     * @param game : Game model.
     * @param images : If we play with the values nothing happens, but with the image we have to make the area empty.
     */
    public void restart (game.Taquin game, boolean images) {
        if (images) {
            super.removeAll();
            super.setBackground(Color.black);
            this.initGame(game);
        }
    }

    /**
     * Find the component id.
     * @param comp : A piece of the game.
     * @return the id of the piece or -1 otherwise.
     */
    public int whichComp (Component comp) {
        for (int i=0; i < grid.length; i++) {
            if (comp == grid[i]) return i;
        }
        return -1;
    }
}
