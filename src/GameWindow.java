import input.KeyboardInput;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    public long lastTime = 0;
    GameCanvas gameCanvas;

    public GameWindow() {
        this.setSize(1024, 600); // set size window
        this.setupGameCanvas();
        this.event();
        this.setVisible(true);
    }

    private void setupGameCanvas() {
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
    }


    private void event() {
        this.windowEvent();
        this.keyboardEvent();
    }

    private void keyboardEvent() {
        this.addKeyListener(KeyboardInput.instance);
    }

    private void windowEvent() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }

        });
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 17_000_000) {
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }
        }
    }

}