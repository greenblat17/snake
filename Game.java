import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private int countDots = 3;
    private final int COUNT_DOT = 50;
    private int[] x = new int[COUNT_DOT];
    private int[] y = new int[COUNT_DOT];
    private int appleX;
    private int appleY;
    private final int SIZE_DOT = 16;
    private Image apple;
    private Image dot;
    private boolean startGame = true;
    Timer timer;
    private boolean up = false;
    private boolean down = true;
    private boolean right = false;
    private boolean left = false;

    public Game() {
        setBackground(Color.BLACK);
        loadImage();
        addKeyListener(new FieldKeyListener());
        initGame();
        setFocusable(true);
    }

    private void loadImage() {
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    private void generatePositionApple() {
        Random random = new Random();
        appleX = random.nextInt(COUNT_DOT - 1) * SIZE_DOT;
        appleY = random.nextInt(COUNT_DOT - 1) * SIZE_DOT;
    }

    private void initGame() {
        for (int i = 0; i < countDots; i++) {
            x[i] = 0;
            y[i] = 48 - i * SIZE_DOT;
        }
        timer = new Timer(250, this);
        timer.start();
        generatePositionApple();
    }

    public void move() {
        for (int i = countDots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
            System.out.println(x[i] + " : " + y[i] + " : " + i);
        }
        if (right) x[0] += SIZE_DOT;
        if (left) x[0] -= SIZE_DOT;
        if (up) y[0] -= SIZE_DOT;
        if (down) y[0] += SIZE_DOT;
        System.out.println(x[0] + " : "  + y[0] + " : 0");

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (startGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < countDots; i++) {
                g.drawImage(dot, x[i], y[i], this);
                //System.out.println(x[i] + " : " + y[i] + " : " + i);
            }
        }  else {
            setBackground(Color.white);
            g.drawString("Game over", 400, 400);
        }
    }

    private void checkEatApple() {
        if (x[0] == appleX && y[0] == appleY) {
            countDots++;
            System.out.println(Arrays.toString(x));
            generatePositionApple();
        }
    }

    private void checkDeath() {
        for (int i = 1; i < countDots; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {
                startGame = false;
            }
        }

        if (x[0] >= SIZE_DOT * COUNT_DOT) {
            startGame = false;
        } else if (x[0] < 0) {
            startGame= false;
        } else if (y[0] < 0) {
            startGame = false;
        } else if( y[0] >= SIZE_DOT * COUNT_DOT) {
            startGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (startGame) {
            move();
            checkEatApple();
            checkDeath();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key  =e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            } else if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }  else if (key  == KeyEvent.VK_UP && !down) {
                up = true;
                right = false;
                left = false;
            } else if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}


