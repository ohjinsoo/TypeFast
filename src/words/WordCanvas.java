package words;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordCanvas extends Canvas {
    private int wordCount = 0;

    private List<Word2D> words = new ArrayList<Word2D>();;
    private JFrame frame;
    private Random rand = new Random();
    private boolean isGameOver = false;
    private char keyPressed = ' ';

    public WordCanvas(JFrame frame) {
        this.frame = frame;
        this.init();
        System.out.println(words.size());
    }

    private void init() {
        setFocusable(false);
        setIgnoreRepaint(true);
        this.setSize(400, 400);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        if (isGameOver) {
            g.drawString("GAME OVER SCORE: " + wordCount, 30, 200);
            g.drawString("Press 'y' to restart.", 130, 235);
            return;
        }

        for (Word2D word : words) {
            g.drawString(word.s, word.x, word.y);
        }
    }

    public int update() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyPressed = e.getKeyChar();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        if (isGameOver && keyPressed == 'y') {
            System.out.println(keyPressed);
            keyPressed = ' ';
            return 2;
        }
        else if (isGameOver)
            return 1;

        if (words.size() > 0 && keyPressed == words.get(0).s.charAt(0)) {
            keyPressed = ' ';
            words.get(0).s = words.get(0).s.substring(1);
            if (words.get(0).s.length() == 0) {
                wordCount++;
                words.remove(0);
            }
        }

        for (Word2D word : words) {
            word.y += 5;

            if (word.y >= 400) {
                endGame();

                // Game IS over, but return 0 because it needs to repaint "Game Over, press y" text.
                return 0;
            }
        }
        return 0;
    }

    public void endGame() {
        words.clear();
        isGameOver = true;
    }

    public void addWord(String nextWord) {
        if (!isGameOver)
            words.add(new Word2D(nextWord, rand.nextInt(50) + 150, 0));
    }
}
