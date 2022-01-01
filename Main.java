import javax.swing.*;

public class Main {
    public Main() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocation(400,400);
        frame.add(new Game());
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();

    }
}
