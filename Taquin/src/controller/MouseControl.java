package controller;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

import game.Taquin;
import GUI.Grid;

public class MouseControl implements MouseInputListener {

    private Taquin game;
    private Grid grid;
    private boolean images;

    /**
     * Added mouse controls.
     * @param game : Game model.
     * @param grid : Game grid.
     * @param images : Boolean that indicates whether to use images in the GUI.
     * 
     */
    public MouseControl (game.Taquin game, GUI.Grid grid, boolean images) {
        this.game = game;
        this.grid = grid;
        this.images = images;
    }

    @Override
    public void mouseClicked (MouseEvent e) {}

    @Override
    public void mouseEntered (MouseEvent e) {}

    @Override
    public void mouseExited (MouseEvent e) {}

    @Override
    public void mousePressed (MouseEvent e) {}

    @Override
    public void mouseReleased (MouseEvent e) {
        if (e.getButton() == 2) {
            game.shuffle();
            grid.restart(game, images);
            grid.displayGame(game.getGame(), images);
        }
        else {
            boolean[] res = game.move(grid.whichComp(e.getComponent()));
            if (res[1]) grid.displayEndGame(game, this.images);
            else if (res[0]) grid.displayGame(game.getGame(), this.images);
        }
    }

    @Override
    public void mouseDragged (MouseEvent e) {}

    @Override
    public void mouseMoved (MouseEvent e) {}
}
