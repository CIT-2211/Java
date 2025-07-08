import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class PixelTankGame extends JFrame {
    private static final int TILE_SIZE = 32;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SPEED = 3;
    private static final int ENEMY_SPAWN_RATE = 60; // 帧数
    private static final int POWER_UP_SPAWN_RATE = 300; // 帧数
    
    private GamePanel gamePanel;
    private GameState gameState;
    private Timer gameTimer;
    private int frameCount = 0;
    private int highScore = 0;
    private boolean twoPlayers = true; // 启用双人模式
    
    public PixelTankGame() {
        setTitle("像素坦克大战");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        gameState = new GameState(twoPlayers); // 传递双人模式设置
        gamePanel = new GamePanel(gameState);
        add(gamePanel);
        
        loadHighScore();
        setupControls();
        startGame();
        
        setVisible(true);
    }
    
    private void loadHighScore() {
        // 这里可以添加从文件加载最高分的代码
        // 简化版：硬编码为0
        highScore = 0;
    }
    
    private void saveHighScore() {
        // 这里可以添加保存最高分的代码
    }
    
    private void setupControls() {
        // 玩家1控制
        InputMap inputMap = gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = gamePanel.getActionMap();
        
        // 玩家1: WASD + 空格射击
        inputMap.put(KeyStroke.getKeyStroke("W"), "up");
        inputMap.put(KeyStroke.getKeyStroke("S"), "down");
        inputMap.put(KeyStroke.getKeyStroke("A"), "left");
        inputMap.put(KeyStroke.getKeyStroke("D"), "right");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "shoot");
        
        actionMap.put("up", new MoveAction(0, -PLAYER_SPEED, 0));
        actionMap.put("down", new MoveAction(0, PLAYER_SPEED, 2));
        actionMap.put("left", new MoveAction(-PLAYER_SPEED, 0, 3));
        actionMap.put("right", new MoveAction(PLAYER_SPEED, 0, 1));
        actionMap.put("shoot", new ShootAction(0));
        
        // 玩家2控制: 方向键 + 小键盘0射击
        inputMap.put(KeyStroke.getKeyStroke("UP"), "p2up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "p2down");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "p2left");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "p2right");
        inputMap.put(KeyStroke.getKeyStroke("NUMPAD0"), "p2shoot");
        
        actionMap.put("p2up", new MoveAction(0, -PLAYER_SPEED, 0, 1));
        actionMap.put("p2down", new MoveAction(0, PLAYER_SPEED, 2, 1));
        actionMap.put("p2left", new MoveAction(-PLAYER_SPEED, 0, 3, 1));
        actionMap.put("p2right", new MoveAction(PLAYER_SPEED, 0, 1, 1));
        actionMap.put("p2shoot", new ShootAction(1));
    }
    
    private void startGame() {
        gameState.initGame();
        
        gameTimer = new Timer(16, e -> {
            frameCount++;
            
            // 更新游戏状态
            gameState.update();
            
            // 生成敌人
            if (frameCount % ENEMY_SPAWN_RATE == 0) {
                spawnEnemy();
            }
            
            // 生成道具
            if (frameCount % POWER_UP_SPAWN_RATE == 0) {
                spawnPowerUp();
            }
            
            // 检查游戏结束
            if (gameState.isGameOver()) {
                gameTimer.stop();
                
                if (gameState.player1.score > highScore) {
                    highScore = gameState.player1.score;
                    saveHighScore();
                }
                
                int option = JOptionPane.showConfirmDialog(this, 
                    "游戏结束！得分: " + gameState.player1.score + 
                    "\n最高分: " + highScore + 
                    "\n再玩一次？", "游戏结束", JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
                    gameState.initGame();
                    frameCount = 0;
                    gameTimer.start();
                } else {
                    System.exit(0);
                }
            }
            
            gamePanel.repaint();
        });
        
        gameTimer.start();
    }
    
    private void spawnEnemy() {
        int difficultyLevel = Math.min(3, gameState.player1.score / 1000 + 1);
        EnemyTank.Difficulty difficulty;
        
        double rand = Math.random();
        if (rand < 0.3) {
            difficulty = EnemyTank.Difficulty.EASY;
        } else if (rand < 0.6) {
            difficulty = EnemyTank.Difficulty.MEDIUM;
        } else {
            difficulty = EnemyTank.Difficulty.HARD;
        }
        
        int x = (int) (Math.random() * (WIDTH - TILE_SIZE));
        int y = (int) (Math.random() * (HEIGHT - TILE_SIZE));
        
        gameState.enemies.add(new EnemyTank(x, y, difficulty, difficultyLevel));
    }
    
    private void spawnPowerUp() {
        int x = (int) (Math.random() * (WIDTH - TILE_SIZE));
        int y = (int) (Math.random() * (HEIGHT - TILE_SIZE));
        
        PowerUp.Type[] types = PowerUp.Type.values();
        PowerUp.Type type = types[(int) (Math.random() * types.length)];
        
        gameState.powerUps.add(new PowerUp(x, y, type));
    }
    
    // 移动动作
    private class MoveAction extends AbstractAction {
        private final int dx, dy;
        private final int direction;
        private final int playerId;
        
        public MoveAction(int dx, int dy, int direction) {
            this(dx, dy, direction, 0);
        }
        
        public MoveAction(int dx, int dy, int direction, int playerId) {
            this.dx = dx;
            this.dy = dy;
            this.direction = direction;
            this.playerId = playerId;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayerTank player = (playerId == 0) ? gameState.player1 : gameState.player2;
            if (player != null) {
                player.move(dx, dy, gameState.walls);
                player.direction = direction;
            }
        }
    }
    
    // 射击动作
    private class ShootAction extends AbstractAction {
        private final int playerId;
        
        public ShootAction(int playerId) {
            this.playerId = playerId;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            PlayerTank player = (playerId == 0) ? gameState.player1 : gameState.player2;
            if (player != null && player.canShoot()) {
                gameState.bullets.add(player.shoot());
            }
        }
    }
    
    // 游戏状态类
    static class GameState {
        PlayerTank player1;
        PlayerTank player2;
        List<EnemyTank> enemies = new ArrayList<>();
        List<Bullet> bullets = new ArrayList<>();
        List<Wall> walls = new ArrayList<>();
        List<PowerUp> powerUps = new ArrayList<>();
        boolean twoPlayers;
        boolean gameOver = false;
        
        public GameState(boolean twoPlayers) {
            this.twoPlayers = twoPlayers;
        }
        
        public void initGame() {
            // 玩家1在左上角，玩家2在右下角
            player1 = new PlayerTank(100, 100, new Color(0, 255, 0)); // 绿色
            if (twoPlayers) {
                player2 = new PlayerTank(WIDTH - 150, HEIGHT - 150, new Color(0, 100, 255)); // 蓝色
            } else {
                player2 = null;
            }
            
            enemies.clear();
            bullets.clear();
            walls.clear();
            powerUps.clear();
            
            generateMap();
            gameOver = false;
        }
        
        private void generateMap() {
            // 生成随机地图
            Random rand = new Random();
            for (int i = 0; i < 50; i++) {
                int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
                int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
                
                // 避免玩家出生点
                if (x < TILE_SIZE * 4 && y < TILE_SIZE * 4) continue;
                if (x > WIDTH - TILE_SIZE * 4 && y > HEIGHT - TILE_SIZE * 4) continue;
                
                Wall.Type type;
                double r = rand.nextDouble();
                if (r < 0.7) type = Wall.Type.BRICK;
                else if (r < 0.9) type = Wall.Type.STEEL;
                else type = Wall.Type.WATER;
                
                walls.add(new Wall(x, y, type));
            }
        }
        
        public void update() {
            // 更新玩家
            player1.update();
            if (player2 != null) player2.update();
            
            // 更新敌人
            Iterator<EnemyTank> enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
                EnemyTank enemy = enemyIter.next();
                enemy.update(player1, walls);
                
                if (enemy.canShoot() && Math.random() < 0.02) {
                    bullets.add(enemy.shoot());
                }
            }
            
            // 更新子弹
            Iterator<Bullet> bulletIter = bullets.iterator();
            List<EnemyTank> enemiesToRemove = new ArrayList<>();
            List<Wall> wallsToRemove = new ArrayList<>();
            
            while (bulletIter.hasNext()) {
                Bullet bullet = bulletIter.next();
                bullet.update();
                
                // 检查子弹是否出界
                if (bullet.x < 0 || bullet.x > WIDTH || bullet.y < 0 || bullet.y > HEIGHT) {
                    bulletIter.remove();
                    continue;
                }
                
                // 检查子弹与墙壁碰撞
                boolean wallHit = false;
                for (Wall wall : walls) {
                    if (wall.isDestructible() && bullet.getBounds().intersects(wall.getBounds())) {
                        bulletIter.remove();
                        if (wall.type == Wall.Type.BRICK) {
                            wallsToRemove.add(wall);
                        }
                        wallHit = true;
                        break;
                    }
                }
                if (wallHit) continue;
                
                // 检查子弹与玩家碰撞
                if (bullet.getBounds().intersects(player1.getBounds())) {
                    player1.takeDamage(bullet.damage);
                    bulletIter.remove();
                    continue;
                }
                
                if (player2 != null && bullet.getBounds().intersects(player2.getBounds())) {
                    player2.takeDamage(bullet.damage);
                    bulletIter.remove();
                    continue;
                }
                
                // 检查子弹与敌人碰撞
                boolean enemyHit = false;
                for (EnemyTank enemy : enemies) {
                    if (bullet.getBounds().intersects(enemy.getBounds())) {
                        enemy.takeDamage(bullet.damage);
                        if (enemy.isDestroyed()) {
                            player1.score += enemy.getScoreValue();
                            enemiesToRemove.add(enemy);
                        }
                        bulletIter.remove();
                        enemyHit = true;
                        break;
                    }
                }
                if (enemyHit) continue;
            }
            
            // 移除被摧毁的敌人和墙壁
            enemies.removeAll(enemiesToRemove);
            walls.removeAll(wallsToRemove);
            
            // 检查道具收集
            Iterator<PowerUp> powerUpIter = powerUps.iterator();
            while (powerUpIter.hasNext()) {
                PowerUp powerUp = powerUpIter.next();
                
                if (player1.getBounds().intersects(powerUp.getBounds())) {
                    applyPowerUp(player1, powerUp.type);
                    powerUpIter.remove();
                    continue;
                }
                
                if (player2 != null && player2.getBounds().intersects(powerUp.getBounds())) {
                    applyPowerUp(player2, powerUp.type);
                    powerUpIter.remove();
                }
            }
            
            // 检查游戏结束
            gameOver = player1.isDestroyed() || (player2 != null && player2.isDestroyed());
        }
        
        private void applyPowerUp(PlayerTank player, PowerUp.Type type) {
            switch (type) {
                case HEALTH:
                    player.health = Math.min(player.maxHealth, player.health + 1);
                    break;
                case SPEED:
                    player.speed = Math.min(5, player.speed + 0.5);
                    break;
                case FIRE_RATE:
                    player.fireRate = Math.max(10, player.fireRate - 5);
                    break;
                case SCORE_BONUS:
                    player.score += 200;
                    break;
            }
        }
        
        public boolean isGameOver() {
            return gameOver;
        }
    }
    
    // 游戏面板
    static class GamePanel extends JPanel {
        private final GameState gameState;
        
        public GamePanel(GameState gameState) {
            this.gameState = gameState;
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // 绘制墙壁
            for (Wall wall : gameState.walls) {
                wall.draw(g);
            }
            
            // 绘制道具
            for (PowerUp powerUp : gameState.powerUps) {
                powerUp.draw(g);
            }
            
            // 绘制子弹
            for (Bullet bullet : gameState.bullets) {
                bullet.draw(g);
            }
            
            // 绘制玩家
            gameState.player1.draw(g);
            if (gameState.player2 != null) {
                gameState.player2.draw(g);
            }
            
            // 绘制敌人
            for (EnemyTank enemy : gameState.enemies) {
                enemy.draw(g);
            }
            
            // 绘制UI
            drawUI(g);
        }
        
        private void drawUI(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("分数: " + gameState.player1.score, 20, 30);
            g.drawString("生命: " + gameState.player1.health, 20, 60);
            
            if (gameState.player2 != null) {
                g.drawString("玩家2 生命: " + gameState.player2.health, WIDTH - 150, 30);
            }
        }
    }
    
    // 坦克基类
    static abstract class Tank {
        int x, y;
        int width = TILE_SIZE, height = TILE_SIZE;
        int direction; // 0:上, 1:右, 2:下, 3:左
        int health;
        int maxHealth = 3;
        double speed = 3;
        int fireRate = 30; // 射击间隔（帧）
        int fireCooldown = 0;
        int damage = 1;
        Color color; // 坦克颜色
        
        public Tank(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.health = maxHealth;
            this.color = color;
        }
        
        public void move(int dx, int dy, List<Wall> walls) {
            int newX = x + dx;
            int newY = y + dy;
            
            // 边界检查
            if (newX < 0 || newX > WIDTH - width || newY < 0 || newY > HEIGHT - height) {
                return;
            }
            
            // 碰撞检查
            Rectangle newBounds = new Rectangle(newX, newY, width, height);
            for (Wall wall : walls) {
                if (newBounds.intersects(wall.getBounds())) {
                    return;
                }
            }
            
            x = newX;
            y = newY;
        }
        
        public boolean canShoot() {
            return fireCooldown <= 0;
        }
        
        public Bullet shoot() {
            fireCooldown = fireRate;
            int bulletX = x + width / 2;
            int bulletY = y + height / 2;
            
            // 根据方向调整子弹初始位置
            switch (direction) {
                case 0: bulletY = y; break;
                case 1: bulletX = x + width; break;
                case 2: bulletY = y + height; break;
                case 3: bulletX = x; break;
            }
            
            return new Bullet(bulletX, bulletY, direction, damage, this);
        }
        
        public void takeDamage(int damage) {
            health -= damage;
        }
        
        public boolean isDestroyed() {
            return health <= 0;
        }
        
        public void update() {
            if (fireCooldown > 0) {
                fireCooldown--;
            }
        }
        
        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
        
        public abstract void draw(Graphics g);
    }
    
    // 玩家坦克
    static class PlayerTank extends Tank {
        int score = 0;
        
        public PlayerTank(int x, int y, Color color) {
            super(x, y, color);
        }
        
        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            
            // 绘制炮管
            int barrelX = x + width / 2;
            int barrelY = y + height / 2;
            int barrelLength = width;
            
            switch (direction) {
                case 0: // 上
                    g.drawLine(barrelX, barrelY, barrelX, barrelY - barrelLength);
                    break;
                case 1: // 右
                    g.drawLine(barrelX, barrelY, barrelX + barrelLength, barrelY);
                    break;
                case 2: // 下
                    g.drawLine(barrelX, barrelY, barrelX, barrelY + barrelLength);
                    break;
                case 3: // 左
                    g.drawLine(barrelX, barrelY, barrelX - barrelLength, barrelY);
                    break;
            }
        }
    }
    
    // 敌人坦克
    static class EnemyTank extends Tank {
        enum Difficulty { EASY, MEDIUM, HARD }
        
        private final Difficulty difficulty;
        private final int level;
        private int aiCooldown = 0;
        private PlayerTank target;
        
        public EnemyTank(int x, int y, Difficulty difficulty, int level) {
            super(x, y, null); // 颜色在下面设置
            this.difficulty = difficulty;
            this.level = level;
            
            // 根据难度设置属性
            switch (difficulty) {
                case EASY:
                    this.color = new Color(255, 100, 100); // 浅红色
                    speed = 1.0 + level * 0.1;
                    damage = 1;
                    maxHealth = 1;
                    break;
                case MEDIUM:
                    this.color = new Color(255, 50, 50); // 中等红色
                    speed = 1.5 + level * 0.15;
                    damage = 2;
                    maxHealth = 2;
                    break;
                case HARD:
                    this.color = new Color(200, 0, 0); // 深红色
                    speed = 2.0 + level * 0.2;
                    damage = 3;
                    maxHealth = 3;
                    break;
            }
            
            health = maxHealth;
        }
        
        public int getScoreValue() {
            switch (difficulty) {
                case EASY: return 100;
                case MEDIUM: return 200;
                case HARD: return 500;
                default: return 0;
            }
        }
        
        public void update(PlayerTank player, List<Wall> walls) {
            super.update();
            target = player;
            
            if (aiCooldown <= 0) {
                // 简单AI：随机移动和转向
                double rand = Math.random();
                if (rand < 0.3) { // 改变方向
                    direction = (int) (Math.random() * 4);
                } else if (rand < 0.7) { // 向玩家移动
                    moveTowardsPlayer();
                }
                aiCooldown = 30;
            } else {
                aiCooldown--;
            }
            
            // 根据方向移动
            switch (direction) {
                case 0: move(0, (int) -speed, walls); break; // 上
                case 1: move((int) speed, 0, walls); break;  // 右
                case 2: move(0, (int) speed, walls); break;   // 下
                case 3: move((int) -speed, 0, walls); break; // 左
            }
        }
        
        private void moveTowardsPlayer() {
            if (target == null) return;
            
            // 计算到玩家的方向
            int dx = target.x - x;
            int dy = target.y - y;
            
            // 决定移动方向
            if (Math.abs(dx) > Math.abs(dy)) {
                direction = (dx > 0) ? 1 : 3;
            } else {
                direction = (dy > 0) ? 2 : 0;
            }
        }
        
        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            
            // 绘制炮管
            int barrelX = x + width / 2;
            int barrelY = y + height / 2;
            int barrelLength = width;
            
            switch (direction) {
                case 0: // 上
                    g.drawLine(barrelX, barrelY, barrelX, barrelY - barrelLength);
                    break;
                case 1: // 右
                    g.drawLine(barrelX, barrelY, barrelX + barrelLength, barrelY);
                    break;
                case 2: // 下
                    g.drawLine(barrelX, barrelY, barrelX, barrelY + barrelLength);
                    break;
                case 3: // 左
                    g.drawLine(barrelX, barrelY, barrelX - barrelLength, barrelY);
                    break;
            }
        }
    }
    
    // 子弹类
    static class Bullet {
        int x, y;
        int width = 6, height = 6;
        int direction;
        int speed = 8;
        int damage;
        Tank owner;
        
        public Bullet(int x, int y, int direction, int damage, Tank owner) {
            this.x = x - width / 2;
            this.y = y - height / 2;
            this.direction = direction;
            this.damage = damage;
            this.owner = owner;
        }
        
        public void update() {
            switch (direction) {
                case 0: y -= speed; break; // 上
                case 1: x += speed; break; // 右
                case 2: y += speed; break; // 下
                case 3: x -= speed; break; // 左
            }
        }
        
        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
        
        public void draw(Graphics g) {
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, width, height);
        }
    }
    
    // 墙壁类
    static class Wall {
        enum Type { BRICK, STEEL, WATER, FOREST }
        
        int x, y;
        Type type;
        
        public Wall(int x, int y, Type type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        public boolean isDestructible() {
            return type == Type.BRICK;
        }
        
        public Rectangle getBounds() {
            return new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
        }
        
        public void draw(Graphics g) {
            switch (type) {
                case BRICK: g.setColor(new Color(150, 75, 0)); break; // 棕色
                case STEEL: g.setColor(Color.GRAY); break;
                case WATER: g.setColor(Color.BLUE); break;
                case FOREST: g.setColor(Color.GREEN); break;
            }
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }
    
    // 道具类
    static class PowerUp {
        enum Type { HEALTH, SPEED, FIRE_RATE, SCORE_BONUS }
        
        int x, y;
        int size = TILE_SIZE;
        Type type;
        
        public PowerUp(int x, int y, Type type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        public Rectangle getBounds() {
            return new Rectangle(x, y, size, size);
        }
        
        public void draw(Graphics g) {
            switch (type) {
                case HEALTH:
                    g.setColor(Color.RED);
                    g.fillOval(x, y, size, size);
                    g.setColor(Color.WHITE);
                    g.drawString("H", x + size/2 - 5, y + size/2 + 5);
                    break;
                case SPEED:
                    g.setColor(Color.CYAN);
                    g.fillOval(x, y, size, size);
                    g.setColor(Color.BLACK);
                    g.drawString("S", x + size/2 - 5, y + size/2 + 5);
                    break;
                case FIRE_RATE:
                    g.setColor(Color.ORANGE);
                    g.fillOval(x, y, size, size);
                    g.setColor(Color.BLACK);
                    g.drawString("F", x + size/2 - 5, y + size/2 + 5);
                    break;
                case SCORE_BONUS:
                    g.setColor(Color.YELLOW);
                    g.fillOval(x, y, size, size);
                    g.setColor(Color.BLACK);
                    g.drawString("$", x + size/2 - 5, y + size/2 + 5);
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PixelTankGame();
        });
    }
}