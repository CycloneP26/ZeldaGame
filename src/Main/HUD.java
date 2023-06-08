package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class HUD extends JPanel {
    private int score = 0;
    private int health = 3;
    private int rupees = 0;
    private int keys = 0;

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    public void setHealth(int health) {
        this.health = health;
        repaint();
    }

    public void setRupees(int rupees) {
        this.rupees = rupees;
        repaint();
    }

    public void setKeys(int keys) {
        this.keys = keys;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

}
}
