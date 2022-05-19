package entity;

import utils.Drawable;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity extends Drawable {
    public Entity(int health, int strength, int speed) {
        this.health = health;
        this.strength = strength;
        this.speed = speed;
    }

    private Position position;
    public BufferedImage idleImage;

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    private int health;
    private int strength;

    public int getSpeed() {
        return speed;
    }

    private int speed;
}
