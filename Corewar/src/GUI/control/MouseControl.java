package GUI.control;

import GUI.*;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class MouseControl implements MouseInputListener{

    private Frame screen;

    /**
     * Mouse controls added.
     * @param screen : Frame where controls are applied.
     */
    public MouseControl(Frame screen){
        this.screen = screen;
    }

    @Override
    public void mousePressed(MouseEvent e){
        JLabel iconMore = this.screen.getIconMore();
        JLabel iconLess = this.screen.getIconLess();
        if(e.getX() >= iconMore.getX() && e.getX() <= 50+iconMore.getX() && e.getY() >= iconMore.getY() && e.getY() <= 50+iconMore.getY()){
            this.screen.increaseSpeed();
        } else if(e.getX() >= iconLess.getX() && e.getX() <= 50+iconLess.getX() && e.getY() >= iconLess.getY() && e.getY() <= 50+iconLess.getY()){
            this.screen.decreaseSpeed();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

} 