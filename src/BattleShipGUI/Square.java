package BattleShipGUI;

import java.awt.Color;
import java.awt.Graphics;

class Square {
    private Color color;
    private int x, y;
    int getX() { return x; }
    int getY() { return y; }

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        color = Color.gray;
    }
    boolean ifalive() {
        return color != Color.blue;
    }


    boolean ifhit(int x, int y) {
        if (this.x == x && this.y == y) {
            color = Color.blue;
            return true;
        }
        return false;
    }
    void paint(Graphics g, int csize, boolean hide) {
        if (!hide || (hide && color == Color.blue)) {
            g.setColor(color);
            g.fill3DRect(x*csize + 1, y*csize + 1, csize - 2, csize - 2, true);
        }
    }
}