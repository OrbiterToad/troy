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
    }

    public Display(){

    }

    public void run() {

    }
}
