package maze.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MovementManager implements KeyListener {

    public static final int SPEED = 10;
    public static final int LOOK_SPEED = 3;
    public boolean left = false;
    public boolean down = false;
    public boolean right = false;
    public boolean up = false;
    public boolean lookLeft = false;
    public boolean lookRight = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_Q:
                lookLeft = true;
            case KeyEvent.VK_E:
                lookRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_Q:
                lookLeft = false;
            case KeyEvent.VK_E:
                lookRight = false;
        }
    }
}
