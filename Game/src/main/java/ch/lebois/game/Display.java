package ch.lebois.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Display extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 600;
    private boolean running = false;
    private Thread thread;

    public Display() {
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);

        state = new StateMachine(this);
        state.setStates((byte)0);
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


    public int FPS;

    public static StateMachine state;





    public void run() {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int frames = 0;

        this.createBufferStrategy(3);
        BufferStrategy bs = this.getBufferStrategy();
        while(running){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                FPS = frames;
                frames = 0;
            }

            draw(bs);
            update(delta);

            try {
                Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000);
            }catch(Exception e){};


        }
    }

    public void draw(BufferStrategy bs){
        do {
            do {
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0,0, WIDTH + 50, HEIGHT + 50);

                state.draw(g);

                g.dispose();
            }while (bs.contentsRestored());
            bs.show();
        }while (bs.contentsLost());
    }

    public void update(double delta){
        state.update(delta);
    }

}
