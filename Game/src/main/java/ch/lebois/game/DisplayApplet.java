package ch.lebois.game;

import java.awt.BorderLayout;
import javax.swing.JApplet;

public class DisplayApplet extends JApplet {
    private Display display = new Display();

    public void init() {
        setLayout(new BorderLayout());
        add(display);
    }

    public void start() {
        display.start();
    }

    public void stop() {
        display.stop();
    }
}
