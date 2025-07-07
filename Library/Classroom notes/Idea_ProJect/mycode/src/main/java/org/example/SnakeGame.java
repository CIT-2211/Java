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
        setTitle("经典像素贪吃蛇");
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
        // 初始化蛇的位置
        for (int i = 0; i < bodyParts; i++) {
            x[i] = (bodyParts - i - 1) * UNIT_SIZE;
            y[i] = UNIT_SIZE * 5; // 从第5行开始
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
            // 绘制食物(红色像素块)
            g.setColor(Color.RED);
            g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // 绘制蛇
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN); // 蛇头
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    // 蛇身使用不同深浅的绿色
                    g.setColor(new Color(0, 150 - i*10, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // 绘制网格线(像素风格) - 更细更淡的线
            g.setColor(new Color(40, 40, 40));
            for (int i = 0; i <= WIDTH / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
            }
            for (int i = 0; i <= HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
            }

            // 绘制分数
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("分数: " + applesEaten, 10, 25);
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

            // 随着分数增加，游戏速度加快
            if (applesEaten % 5 == 0 && DELAY > 40) {
                timer.setDelay(timer.getDelay() - 10);
            }
        }
    }

    public void checkCollisions() {
        // 检查是否撞到自己
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                gameOver = true;
            }
        }

        // 检查是否撞墙
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
            gameOver = true;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOverScreen(Graphics g) {
        // 游戏结束背景
        g.setColor(new Color(0, 0, 0, 200)); // 半透明黑色
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 显示游戏结束文字
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("游戏结束", (WIDTH - metrics.stringWidth("游戏结束")) / 2, HEIGHT / 2 - 40);

        // 显示分数
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreText = "最终分数: " + applesEaten;
        g.drawString(scoreText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, HEIGHT / 2 + 10);

        // 显示重新开始提示
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String restartText = "按 R 键重新开始";
        g.drawString(restartText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(restartText)) / 2, HEIGHT / 2 + 60);
    }

    public void pauseScreen(Graphics g) {
        // 暂停背景
        g.setColor(new Color(0, 0, 0, 150)); // 半透明黑色
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 显示暂停文字
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("游戏暂停", (WIDTH - metrics.stringWidth("游戏暂停")) / 2, HEIGHT / 2 - 20);

        // 显示继续提示
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String continueText = "按 P 键继续游戏";
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