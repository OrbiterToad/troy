package com.entwicklung.Display;

<<<<<<< Updated upstream:Game/src/main/java/ch/lebois/game/Display.java
import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
=======

>>>>>>> Stashed changes:Game/src/main/java/com/entwicklung/Display/Display.java

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
<<<<<<< Updated upstream:Game/src/main/java/ch/lebois/game/Display.java
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
=======
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    public static int WIDTH = 800, HEIGHT = 600;
    public int FPS;

    public Display(){
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
    }

    public void run() {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int frames = 0;

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

            try {
                Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000);
            }catch(Exception e){};

>>>>>>> Stashed changes:Game/src/main/java/com/entwicklung/Display/Display.java
            System.out.print("This is running!");
        }
    }
}
