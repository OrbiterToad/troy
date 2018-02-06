package ch.lebois.game;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface SuperStateMachine {

    void update(double delta);

    void draw(Graphics2D g);

    void init(Canvas canvas);
}
