package BattleShipGUI;

import java.awt.Graphics;
import java.util.ArrayList;

class Nave {
    private ArrayList<Square> squares = new ArrayList<>();
    Nave(int x, int y, int n, int pos) {
        for (int i = 0; i < n; i++)
            squares.add(new Square(x + i * ((pos == 1)? 0 : 1), y + i * ((pos == 1)?1:0)));
    }

    boolean Notinfield(int down, int up) {
        for (Square square : squares)
            if (square.getX() < down || square.getX() > up || square.getY() < down || square.getY() > up)
                return true;
        return false;
    }

    boolean Near(Nave nearnave) {
        for (Square square : squares)
            if (nearnave.Nearcell(square))
                return true;
        return false;
    }

    boolean Ifhit(int x, int y) {
        for (Square square : squares)
            if (square.ifhit(x, y)) return true;
        return false;
    }
    boolean ifalive() {
        for (Square square : squares)
            if (square.ifalive()) return true;
        return false;
    }
    boolean Nearcell(Square nearnave) {
        for (Square square : squares)
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++)
                    if (nearnave.getX() == square.getX() + dx && nearnave.getY() == square.getY() + dy)
                        return true;
        return false;
    }



    void paint(Graphics g, int cellSize, boolean hide) {
        for (Square square : squares)  square.paint(g, cellSize, hide);
    }
}