import words.RandomWords;
import words.WordCanvas;

import javax.swing.*;


public class Game {
    private RandomWords rw = new RandomWords();
    private JFrame frame;
    private WordCanvas can;
    private int time;

    private int wordRate = 50;

    public Game() {
        this.frame = new JFrame("test");
        init();
    }

    private void init() {
        time = 49;
        frame.getContentPane().removeAll();
        frame.repaint();
        can = new WordCanvas(frame);
        frame.setResizable(false);
        frame.add(can);
        frame.pack();
        frame.setVisible(true);
        can.createBufferStrategy(3);
    }

    public void update() {
        time++;

        // update: 0 = continue, 1 = endgame, 2 = restartgame. TODO: Make into enum whenever youre not lazy.
        int update = can.update();
        if (update != 1)
            can.repaint();

        if (time % wordRate == 0) {
            can.addWord(rw.getNextWord());
        }

        if (update == 2) {
            init();
        }
    }
}
