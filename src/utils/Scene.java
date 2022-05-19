package utils;

import entity.Player;

import java.awt.*;

public abstract class Scene {

  private Music ambianceMusic;

  public Scene(String pathToMusic) {
    ambianceMusic = new Music(pathToMusic);
  }

  public Scene() {
  }

  public void playMusic() {
    if (ambianceMusic != null) {
      ambianceMusic.play();
    }
  }

  public abstract void update(Player player);

  public abstract void draw(Graphics2D g2);

  public void reset() {
    if (ambianceMusic != null) {
      ambianceMusic.stop();
    }
  };
}
