import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {

    private boolean running = false;
    private Game game = new Game();

    public static void main(String[] args) {
        new Main().run();
    }


    public void run() {
        running = true;
        Timer timer = new Timer(20 , this::update);
        timer.setRepeats(true);
        timer.start();
    }

    private void update(ActionEvent e) {
        game.update();
    }
}
