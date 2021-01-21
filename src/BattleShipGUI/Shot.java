package BattleShipGUI;

import java.awt.Color;
import java.awt.Graphics;

class Shot {
    private int x, y;
    private boolean shot;

    int getX() { return x; }
    int getY() { return y; }

    Shot(int x, int y, boolean shot) {
        this.shot = shot;
        this.x = x;
        this.y = y;
    }

    boolean isShot() { return shot; }
    void paint(Graphics g, int cellSize) {
        g.setColor(Color.red);
        if (shot) g.fillRect(x*cellSize + cellSize/2 - 3, y*cellSize + cellSize/2 - 3, 8, 8);
        else g.drawRect(x*cellSize + cellSize/2 - 3, y*cellSize + cellSize/2 - 3, 8, 8);
    }
}