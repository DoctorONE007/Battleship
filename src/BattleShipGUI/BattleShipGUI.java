package BattleShipGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BattleShipGUI extends JFrame {
    private int countall=0;
    private int mouseclick = 1;
    private JTextArea textArea;
    private Scrim lsidepan;
    private Naves ship;
    private Shots shots;
    private boolean gameend;

    public static void main(String[] args) {
        new BattleShipGUI();
    }
    class Scrim extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int i1 = (int) getSize().getWidth() / 10;
            g.setColor(Color.blue);
            for (int i = 1; i < 10; i++) {
                g.drawLine(0, i*i1, 10*i1, i*i1);
                g.drawLine(i*i1, 0, i*i1, 10*i1);
            }
            if (i1 == 40) {
                shots.paint(g);
                ship.paint(g);
            }
        }
    }
    private BattleShipGUI() {
        setTitle("Battle Ship");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lsidepan = new Scrim();
        lsidepan.setPreferredSize(new Dimension(400, 400));
        lsidepan.setBorder(BorderFactory.createLineBorder(Color.blue));
        lsidepan.setBackground(Color.black);
        lsidepan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/40;
                int y = e.getY()/40;
                if (e.getButton() == mouseclick && !gameend) // left mouse
                    if (!shots.hitSamePlace(x, y)) {
                        shots.add(x, y, true);
                        if (ship.ifhit(x, y)) {
                            textArea.append("\n" +"Shoot at: "+x+" "+y+" -hit"+". Allshoots: "+ ++countall);
                            if (!ship.ifsurvive()) {
                                gameend = true;
                                JOptionPane.showMessageDialog(null, "You won");
                            }
                        }else textArea.append("\n" +"Shoot at: "+x+" "+y+" -miss"+". Allshoots: "+ ++countall);
                        lsidepan.repaint();
                    } else JOptionPane.showMessageDialog(null, "You have already shoot here");
            }
        });

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        JButton newGame = new JButton("New game");
        newGame.addActionListener(e -> {
            ship = new Naves(10, 40, true);
            shots = new Shots(40);
            lsidepan.repaint();
            textArea.selectAll();
            textArea.replaceSelection("");
            gameend = false;
            countall=0;
        });
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout());
        buttons.add(newGame);
        buttons.add(exit);

        textArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);

        JPanel rsidepan = new JPanel();
        rsidepan.add(scrollPane);
        rsidepan.setLayout(new BorderLayout());

        rsidepan.add(scrollPane, BorderLayout.CENTER);
        rsidepan.add(buttons, BorderLayout.SOUTH);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(rsidepan);
        add(lsidepan);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        shots = new Shots(40);
        ship = new Naves(10, 40, true);
        gameend = false;
    }
}