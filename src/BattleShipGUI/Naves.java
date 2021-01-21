package BattleShipGUI;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

class Naves {
    private final int size;
    private ArrayList<Nave> naves = new ArrayList<>(); // array for ship
    private final int[] allships = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; // pattern for ships
    private Random random;
    private boolean visible;

    Naves(int fsize, int csize, boolean visible) {
        random = new Random();
        for (int i = 0; i < allships.length; i++) {
            Nave nave;
            do {
                int x = random.nextInt(fsize);
                int y = random.nextInt(fsize);
                int pos = random.nextInt(2);
                nave = new Nave(x, y, allships[i], pos);
            } while (nave.Notinfield(0, fsize - 1) || iftouch(nave));
            naves.add(nave);
        }
        size = csize;
        this.visible = visible;
    }
    boolean ifsurvive() {
        for (Nave nave : naves) if (nave.ifalive()) return true;
        return false;
    }
    boolean ifhit(int x, int y) {
        for (Nave nave : naves) if (nave.Ifhit(x, y)) return true;
        return false;
    }
    boolean iftouch(Nave nave1) {
        for (Nave nave : naves) if (nave.Near(nave1)) return true;
        return false;
    }
 void paint(Graphics g) {
        for (Nave nave : naves) nave.paint(g, size, visible);
    }
}