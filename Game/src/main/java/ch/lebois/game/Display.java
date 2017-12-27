package ch.lebois.game;

import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Display extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 600;
    private boolean running = false;
    private Thread thread;

    public Display() {
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
    }

    public static void main(String[] args) {
        Display display = new Display();
        JFrame frame = new JFrame();
        frame.add(display);
        frame.pack();
        frame.setTitle("Space Shooter");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        display.start();
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
            System.out.print("This is running!");
        }
    }
}
