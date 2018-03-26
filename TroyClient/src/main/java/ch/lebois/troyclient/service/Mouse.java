package ch.lebois.troyclient.service;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * @author Felix
 * @date 26.03.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyclient.service
 **/
public class Mouse {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void mouseClick() {
        try {
            Robot bot = new Robot();
            bot.mousePress(InputEvent.BUTTON1_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void moveMouse(String command) {
        String[] splits = command.split(" ");
        setX(Integer.valueOf(splits[1]));
        setY(Integer.valueOf(splits[2]));
        try {
            new Robot().mouseMove(getX(), getY());
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
