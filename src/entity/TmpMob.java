package entity;

import java.awt.*;

public class TmpMob extends  Entity {

    public TmpMob(int health, int strength, int speed){
        super(health, strength, speed);
    }
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillRect(430, 200, 70, 100);
    }
}
