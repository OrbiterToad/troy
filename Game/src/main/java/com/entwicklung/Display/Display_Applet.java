package com.entwicklung.Display;

import javax.swing.JApplet;
import java.awt.*;

public class Display_Applet extends JApplet{
    private static final long serialVersionUID = 1L;
    private Display display = new Display();

    public void init(){
        setLayout(new BorderLayout());
        add(display);
    }

    public void start(){
        display.start();
    }

    public void stop(){
        display.stop();
    }
}
