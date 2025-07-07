import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 游戏状态类
 * 管理游戏中的所有实体和状态，处理游戏逻辑更新和碰撞检测
 */
public class GameState {
    // 游戏状态枚举
    public enum State {
        MENU, PLAYING, PAUSED, GAME_OVER
    }
    
    private State currentState;
    private PlayerTank player1;
    private PlayerTank player2;
    private List<EnemyTank> enemies;
    private List<Bullet> bullets;
    private List<PowerUp> powerUps;
    private GameMap map;
    private int score;
    private int highScore;
    private int lives;
    private int level;
    private int enemySpawnCounter;
    private int difficultyLevel;
    private Random random;
    
    /**
     * 构造方法
     */
    public GameState() {
        currentState = State.MENU;
        map = new GameMap();
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        powerUps = new ArrayList<>();
        random = new Random();
        score = 0;
        highScore = 0;
        lives = 3;
        level = 1;
        enemySpawnCounter = 0;
        difficultyLevel = 1;
        
        // 初始化玩家坦克
        initPlayers();
        
        // 加载最高分
        loadHighScore();
    }
    
    /**
     * 初始化玩家坦克
     */
    private void initPlayers() {
        // 玩家1出生位置（左下角）
        player1 = new PlayerTank(1, 32, map.getMapHeightInPixels() - 64);
        
        // 玩家2初始为null，在双人模式下初始化
        player2 = null;
    }
    
    /**
     * 开始新游戏
     * @param isTwoPlayer 是否双人模式
     */
    public void startNewGame(boolean isTwoPlayer) {
        score = 0;
        lives = 3;
        level = 1;
        difficultyLevel = 1;
        enemies.clear();
        bullets.clear();
        powerUps.clear();
        
        map.generateMap();
        initPlayers();
        
        if (isTwoPlayer) {
            // 玩家2出生位置（右下角）
            player2 = new PlayerTank(2, map.getMapWidthInPixels() - 64, map.getMapHeightInPixels() - 64);
        }
        
        currentState = State.PLAYING;
    }
    
    /**
     * 更新游戏状态
     */
    public void update() {
        if (currentState != State.PLAYING) return;
        
        // 更新玩家
        updatePlayers();
        
        // 更新敌人
        updateEnemies();
        
        // 更新子弹
        updateBullets();
        
        // 更新道具
        updatePowerUps();
        
        // 处理碰撞检测
        checkCollisions();
        
        // 生成敌人
        spawnEnemies();
        
        // 检查游戏是否结束
        checkGameOver();
    }
    
    /**
     * 更新玩家状态
     */
    private void updatePlayers() {
        player1.move();
        if (player2 != null) {
            player2.move();
        }
    }
    
    /**
     * 更新敌人状态
     */
    private void updateEnemies() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            EnemyTank enemy = enemies.get(i);
            enemy.move();
            
            // 敌人射击
            Bullet bullet = enemy.fire();
            if (bullet != null) {
                bullets.add(bullet);
            }
            
            // 移除死亡的敌人
            if (!enemy.isAlive()) {
                enemies.remove(i);
                addScore(getEnemyScore(enemy));
                
                // 有几率生成道具
                if (random.nextDouble() < 0.3) { // 30%几率生成道具
                    spawnPowerUp(enemy.getX(), enemy.getY());
                }
            }
        }
    }
    
    /**
     * 更新子弹状态
     */
    private void updateBullets() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.move();
            
            if (!bullet.isActive()) {
                bullets.remove(i);
            }
        }
    }
    
    /**
     * 更新道具状态
     */
    private void updatePowerUps() {
        for (int i = powerUps.size() - 1; i >= 0; i--) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.update();
            
            if (!powerUp.isActive()) {
                powerUps.remove(i);
            }
        }
    }
    
    /**
     * 处理所有碰撞检测
     */
    private void checkCollisions() {
        // 子弹与墙体碰撞
        checkBulletWallCollisions();
        
        // 子弹与坦克碰撞
        checkBulletTankCollisions();
        
        // 坦克与墙体碰撞
        checkTankWallCollisions();
        
        // 玩家与道具碰撞
        checkPlayerPowerUpCollisions();
    }
    
    /**
     * 检查子弹与墙体碰撞
     */
    private void checkBulletWallCollisions() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            Wall wall = map.getWallAtPixel(bullet.getX(), bullet.getY());
            
            if (wall != null && !wall.isPassable()) {
                // 子弹击中墙体
                bullet.setActive(false);
                
                // 如果墙体可破坏，移除墙体
                if (wall.isDestructible()) {
                    map.removeWallAtPixel(bullet.getX(), bullet.getY());
                }
            }
        }
    }
    
    /**
     * 检查子弹与坦克碰撞
     */
    private void checkBulletTankCollisions() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            
            // 玩家子弹击中敌人
            if (bullet.getOwner() instanceof PlayerTank) {
                for (int j = enemies.size() - 1; j >= 0; j--) {
                    EnemyTank enemy = enemies.get(j);
                    if (bullet.collidesWith(enemy)) {
                        enemy.takeDamage(bullet.getDamage());
                        bullet.setActive(false);
                        break;
                    }
                }
            }
            // 敌人子弹击中玩家
            else if (bullet.getOwner() instanceof EnemyTank) {
                if (bullet.collidesWith(player1)) {
                    player1.takeDamage(bullet.getDamage());
                    bullet.setActive(false);
                }
                
                if (player2 != null && bullet.collidesWith(player2)) {
                    player2.takeDamage(bullet.getDamage());
                    bullet.setActive(false);
                }
            }
        }
    }
    
    /**
     * 检查坦克与墙体碰撞
     */
    private void checkTankWallCollisions() {
        // 玩家1与墙体碰撞
        checkTankWallCollision(player1);
        
        // 玩家2与墙体碰撞
        if (player2 != null) {
            checkTankWallCollision(player2);
        }
        
        // 敌人与墙体碰撞
        for (EnemyTank enemy : enemies) {
            // 困难敌人有几率穿墙
            if (enemy.getDifficulty() == EnemyTank.Difficulty.HARD && enemy.canPassThroughWalls()) {
                continue;
            }
            checkTankWallCollision(enemy);
        }
    }
    
    /**
     * 检查单个坦克与墙体的碰撞
     */
    private void checkTankWallCollision(Tank tank) {
        Wall wall = map.getWallAtPixel(tank.getX(), tank.getY());
        if (wall != null && !wall.isPassable()) {
            // 简单的碰撞处理：将坦克移回碰撞前的位置
            // 这里需要更复杂的位置修正逻辑，简化处理为停止移动
            switch (tank.getDirection()) {
                case 0: tank.setY(tank.getY() + tank.getSpeed()); break; // 上
                case 1: tank.setX(tank.getX() - tank.getSpeed()); break; // 右
                case 2: tank.setY(tank.getY() - tank.getSpeed()); break; // 下
                case 3: tank.setX(tank.getX() + tank.getSpeed()); break; // 左
            }
        }
    }
    
    /**
     * 检查玩家与道具碰撞
     */
    private void checkPlayerPowerUpCollisions() {
        for (int i = powerUps.size() - 1; i >= 0; i--) {
            PowerUp powerUp = powerUps.get(i);
            
            if (player1.getBounds().intersects(powerUp.getBounds())) {
                powerUp.applyEffect(player1);
                powerUps.remove(i);
                continue;
            }
            
            if (player2 != null && player2.getBounds().intersects(powerUp.getBounds())) {
                powerUp.applyEffect(player2);
                powerUps.remove(i);
            }
        }
    }
    
    /**
     * 生成敌人坦克
     */
    private void spawnEnemies() {
        enemySpawnCounter++;
        
        // 控制敌人生成频率，随难度增加而提高
        int spawnRate = Math.max(30, 120 - difficultyLevel * 10);
        
        if (enemySpawnCounter >= spawnRate) {
            enemySpawnCounter = 0;
            
            // 控制最大敌人数量
            int maxEnemies = 5 + difficultyLevel;
            if (enemies.size() < maxEnemies) {
                spawnEnemy();
            }
        }
    }
    
    /**
     * 生成单个敌人坦克
     */
    private void spawnEnemy() {
        // 随机生成敌人位置（地图上方）
        int x = random.nextInt(map.getMapWidthInPixels() - 64) + 32;
        int y = random.nextInt(100) + 32;
        
        // 根据难度决定敌人类型比例
        EnemyTank.Difficulty difficulty;
        double rand = random.nextDouble();
        
        if (rand < 0.5 - difficultyLevel * 0.05) { // 简单敌人
            difficulty = EnemyTank.Difficulty.EASY;
        } else if (rand < 0.8 - difficultyLevel * 0.05) { // 中等敌人
            difficulty = EnemyTank.Difficulty.MEDIUM;
        } else { // 困难敌人
            difficulty = EnemyTank.Difficulty.HARD;
        }
        
        // 创建敌人坦克
        EnemyTank enemy = new EnemyTank(difficulty, x, y, player1);
        enemies.add(enemy);
    }
    
    /**
     * 生成道具
     */
    private void spawnPowerUp(int x, int y) {
        PowerUp powerUp = PowerUp.createRandomPowerUp(x, y);
        powerUps.add(powerUp);
    }
    
    /**
     * 检查游戏是否结束
     */
    private void checkGameOver() {
        if (!player1.isAlive() && (player2 == null || !player2.isAlive())) {
            // 所有玩家都死亡，游戏结束
            if (score > highScore) {
                highScore = score;
                saveHighScore();
            }
            currentState = State.GAME_OVER;
        }
    }
    
    /**
     * 添加分数
     */
    public void addScore(int points) {
        score += points;
        
        // 根据分数增加难度
        int newDifficulty = score / 1000 + 1;
        if (newDifficulty > difficultyLevel) {
            difficultyLevel = newDifficulty;
            level = difficultyLevel;
        }
    }
    
    /**
     * 获取敌人的分数值
     */
    private int getEnemyScore(EnemyTank enemy) {
        switch (enemy.getDifficulty()) {
            case EASY: return 100;
            case MEDIUM: return 200;
            case HARD: return 300;
            default: return 100;
        }
    }
    
    /**
     * 玩家射击
     */
    public void playerFire(int playerId) {
        if (currentState != State.PLAYING) return;
        
        Bullet bullet;
        if (playerId == 1) {
            bullet = player1.fire();
        } else if (playerId == 2 && player2 != null) {
            bullet = player2.fire();
        } else {
            return;
        }
        
        if (bullet != null) {
            bullets.add(bullet);
        }
    }
    
    /**
     * 设置玩家方向
     */
    public void setPlayerDirection(int playerId, int direction) {
        if (playerId == 1) {
            player1.setDirection(direction);
        } else if (playerId == 2 && player2 != null) {
            player2.setDirection(direction);
        }
    }
    
    /**
     * 加载最高分
     */
    private void loadHighScore() {
        // 实际实现中应该从文件读取
        highScore = 0;
    }
    
    /**
     * 保存最高分
     */
    private void saveHighScore() {
        // 实际实现中应该保存到文件
    }
    
    // Getters and Setters
    public State getCurrentState() { return currentState; }
    public void setCurrentState(State currentState) { this.currentState = currentState; }
    
    public PlayerTank getPlayer1() { return player1; }
    public PlayerTank getPlayer2() { return player2; }
    
    public List<EnemyTank> getEnemies() { return enemies; }
    public List<Bullet> getBullets() { return bullets; }
    public List<PowerUp> getPowerUps() { return powerUps; }
    
    public GameMap getMap() { return map; }
    
    public int getScore() { return score; }
    public int getHighScore() { return highScore; }
    public int getLives() { return lives; }
    public int getLevel() { return level; }
}