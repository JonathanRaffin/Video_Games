package controller;

import java.awt.event.*;

import game.Taquin;
import GUI.Grid;

public class KeyControl implements KeyListener {
    
    private Taquin game;
    private Grid grid;
    private boolean images;

    /**
     * Added keyboard controls.
     * @param game : Game model.
     * @param grid : Game grid.
     * @param images : Boolean that indicates whether to use images in the GUI.
     */
    public KeyControl (Taquin game, Grid grid, boolean images) {
        this.game = game;
        this.grid = grid;
        grid.displayGame(game.getGame(), images);
        this.images = images;
    }
    
    public void keyTyped (KeyEvent e) {}

    public void keyPressed (KeyEvent e) {}

    public void keyReleased (KeyEvent e) {
        boolean[] res = new boolean[2];
        switch (e.getKeyCode()) { 
            case KeyEvent.VK_UP :
                res = game.move('d');
                break;
            case KeyEvent.VK_DOWN :
                res = game.move('u');
                break;
            case KeyEvent.VK_LEFT :
                res = game.move('l');
                break;
            case KeyEvent.VK_RIGHT :
                res = game.move('r');
                break;
            case KeyEvent.VK_R :
                game.shuffle();
                grid.restart(game, images);
                grid.displayGame(game.getGame(), images);
                break;
            case KeyEvent.VK_ESCAPE :
                System.exit(0);
                break;
        }
        if (res[1]) grid.displayEndGame(game, this.images);
        else if (res[0]) grid.displayGame(game.getGame(), this.images);
    }
}
