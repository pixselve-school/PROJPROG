package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Function;

public class KeyHandler implements KeyListener {
  public Function<Integer, Void> onKeyPress = (Integer a) -> {
    return null;
  };
  public Function<Integer, Void> onKeyReleased = (Integer a) -> {
    return null;
  };

  @Override
  public void keyTyped(KeyEvent e) {


  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    onKeyPress.apply(code);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    onKeyReleased.apply(code);

  }

}
