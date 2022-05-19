package entity;

import utils.Drawable;
import utils.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity extends Drawable {
    int m_health;
    int m_strength;
    int m_speed;

    public Entity(int health, int strength, int speed) {
        m_health = health;
        m_strength = strength;
        m_speed = speed;
    }
    public BufferedImage idleImage;

    public int getHealth() {
        return m_health;
    }

    public int getStrength() {
        return m_strength;
    }

    public void setHealth(int damage) {
        m_health = m_health - damage;
    }


    public int getSpeed() {
        return m_speed;
    }
}
