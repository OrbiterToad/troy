package ch.lebois.game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player implements KeyListener {

    private BufferedImage pSprite;
    private Rectangle rect;

    private double posX;
    private double posY;
    private int width;
    private int height;

    public Player(double posX, double posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        rect = new Rectangle((int) posX, (int) posY, width, height);

        try {
            File file = new File("bin/img/player.png");
            pSprite = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(pSprite, (int) posX, (int) posY, width, height, null);
    }

    public void update(double delta) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }
}
