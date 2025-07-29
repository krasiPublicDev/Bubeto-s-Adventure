package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyHandler extends KeyAdapter {
    private final LinkedList<String> directionStack = new LinkedList<>();

    public String getCurrentDirection() {
        return directionStack.isEmpty() ? null : directionStack.getLast();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        String dir = keyToDirection(e.getKeyCode());
        if (dir != null && !directionStack.contains(dir)) {
            directionStack.add(dir);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String dir = keyToDirection(e.getKeyCode());
        directionStack.remove(dir);
    }

    private String keyToDirection(int keyCode) {
        return switch (keyCode) {
            case KeyEvent.VK_W -> "up";
            case KeyEvent.VK_S -> "down";
            case KeyEvent.VK_A -> "left";
            case KeyEvent.VK_D -> "right";
            default -> null;
        };
    }
}

