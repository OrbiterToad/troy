package com.entwicklung.Display;

import javax.swing.*;
import java.awt.*;

public class Display extends Canvas implements Runnable{

    public static void main(String[] args){
        Display display = new Display();
        JFrame frame = new JFrame();
        frame.add(display);
        frame.pack();
        frame. setTitle("Space Shooter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        display.start();
    }

    private boolean running = false;
    private Thread thread;

    public synchronized void start(){
        if(running)
            return;

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {e.printStackTrace();}
    }

    public static int WIDTH = 800, HEIGHT = 600;

    public Display(){
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
    }

    public void run() {
        while(running){
            System.out.print("This is running!");
        }
    }
}
