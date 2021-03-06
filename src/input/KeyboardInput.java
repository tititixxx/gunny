package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    public static KeyboardInput instance = new KeyboardInput();

    public boolean leftPressed;
    public boolean leftReleased;

    public boolean rightPressed;
    public boolean rightReleased;

    public boolean upPressed;
    public boolean upReleased;

    public boolean downPressed;
    public boolean downReleased;

    public boolean spacePressed;
    public boolean spaceReleased;


    private KeyboardInput() {

    }

    public void reset() {
        this.leftReleased = false;
        this.leftPressed = false;

        this.rightReleased = false;
        this.rightPressed = false;

        this.upReleased = false;
        this.upPressed = false;

        this.downPressed = false;
        this.downReleased = false;

        this.spaceReleased = false;
        this.spacePressed = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.leftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.rightPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.upPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.downPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.spacePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.leftReleased = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.rightReleased = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.upReleased = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.downReleased = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.spaceReleased = true;
        }
    }
}
