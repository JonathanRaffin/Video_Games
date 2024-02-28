package GUI.control;

import GUI.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardControl implements KeyListener{

    private Frame screen;

    /**
     * Keyboard controls added.
     * @param screen : Frame where controls are applied.
     */
    public KeyboardControl(Frame screen){
        this.screen = screen;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_EQUALS :
            case KeyEvent.VK_ADD:
                this.screen.increaseSpeed();
                break;
            case KeyEvent.VK_SUBTRACT :
            case KeyEvent.VK_MINUS:
                this.screen.decreaseSpeed();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
