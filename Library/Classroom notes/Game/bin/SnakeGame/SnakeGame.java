import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }
}

class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("����̰����1.0�汾");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int UNIT_SIZE = 20;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 100;

    private final int[] x = new int[GAME_UNITS];
    private final int[] y = new int[GAME_UNITS];
    private int bodyParts = 6;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;
    private boolean gameOver = false;
    private boolean paused = false;

    public GamePanel() {
        random = new Random();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initializeGame();
    }

    public void initializeGame() {
        // ��ʼ���ߵ�λ��
        for (int i = 0; i < bodyParts; i++) {
            x[i] = (bodyParts - i - 1) * UNIT_SIZE;
            y[i] = UNIT_SIZE * 5; // �ӵ�5�п�ʼ
        }
        newApple();
        running = true;
        gameOver = false;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running && !paused) {
            // ����ʳ��(��ɫ���ؿ�)
            g.setColor(Color.RED);
            g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // ������
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN); // ��ͷ
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    // ����ʹ�ò�ͬ��ǳ����ɫ
                    g.setColor(new Color(0, 150 - i*10, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // ����������(���ط��) - ��ϸ��������
            g.setColor(new Color(40, 40, 40));
            for (int i = 0; i <= WIDTH / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
            }
            for (int i = 0; i <= HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
            }

            // ���Ʒ���
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("����: " + applesEaten, 10, 25);
        } else if (gameOver) {
            gameOverScreen(g);
        } else if (paused) {
            pauseScreen(g);
        }
    }

    public void newApple() {
        boolean onSnake;
        do {
            onSnake = false;
            appleX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
            appleY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;

            for (int i = 0; i < bodyParts; i++) {
                if (x[i] == appleX && y[i] == appleY) {
                    onSnake = true;
                    break;
                }
            }
        } while (onSnake);
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();

            // ���ŷ������ӣ���Ϸ�ٶȼӿ�
            if (applesEaten % 5 == 0 && DELAY > 40) {
                timer.setDelay(timer.getDelay() - 10);
            }
        }
    }

    public void checkCollisions() {
        // ����Ƿ�ײ���Լ�
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                gameOver = true;
            }
        }

        // ����Ƿ�ײǽ
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
            gameOver = true;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOverScreen(Graphics g) {
        // ��Ϸ��������
        g.setColor(new Color(0, 0, 0, 200)); // ��͸����ɫ
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // ��ʾ��Ϸ��������
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("��Ϸ����", (WIDTH - metrics.stringWidth("��Ϸ����")) / 2, HEIGHT / 2 - 40);

        // ��ʾ����
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreText = "���շ���: " + applesEaten;
        g.drawString(scoreText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, HEIGHT / 2 + 10);

        // ��ʾ���¿�ʼ��ʾ
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String restartText = "�� R �����¿�ʼ";
        g.drawString(restartText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(restartText)) / 2, HEIGHT / 2 + 60);
    }

    public void pauseScreen(Graphics g) {
        // ��ͣ����
        g.setColor(new Color(0, 0, 0, 150)); // ��͸����ɫ
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // ��ʾ��ͣ����
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("��Ϸ��ͣ", (WIDTH - metrics.stringWidth("��Ϸ��ͣ")) / 2, HEIGHT / 2 - 20);

        // ��ʾ������ʾ
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String continueText = "�� P ��������Ϸ";
        g.drawString(continueText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(continueText)) / 2, HEIGHT / 2 + 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
            case KeyEvent.VK_R:
                if (gameOver || paused) {
                    resetGame();
                }
                break;
            case KeyEvent.VK_P:
                togglePause();
                break;
        }
    }

    private void resetGame() {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        gameOver = false;
        paused = false;
        timer.setDelay(DELAY);
        initializeGame();
    }

    private void togglePause() {
        if (gameOver) return;

        paused = !paused;
        if (paused) {
            timer.stop();
        } else {
            timer.start();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}