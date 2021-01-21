package BattleShipGUI;

import java.awt.Graphics;
import java.util.ArrayList;

class Shots {
    private final int csize;
    private ArrayList<Shot> allshots;

    Shots(int cellSize) {
        csize = cellSize;
        allshots = new ArrayList<>();
    }
    boolean hitSamePlace(int x, int y) {
        for (Shot shot : allshots)
            if (shot.getX() == x && shot.getY() == y && shot.isShot())
                return true;
        return false;
    }
    void add(int x, int y, boolean shot) {
        allshots.add(new Shot(x, y, shot));
    }
    void paint(Graphics g) {
        for (Shot shot : allshots) shot.paint(g, csize);
    }
}