import java.util.Random;

/**
 * 敌人坦克类
 * 继承自Tank基类，实现AI控制的敌人坦克功能
 */
public class EnemyTank extends Tank {
    // 敌人难度级别
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }
    
    // AI状态
    private enum AIState {
        IDLE, PATROLLING, CHASING, ATTACKING, AVOIDING
    }
    
    private Difficulty difficulty;
    private AIState currentState;
    private Random random;
    private long stateChangeTime;
    private int patrolDirection;
    private long lastDirectionChange;
    private PlayerTank targetPlayer;
    
    /**
     * 构造方法
     * @param difficulty 敌人难度级别
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param targetPlayer 目标玩家坦克
     */
    public EnemyTank(Difficulty difficulty, int x, int y, PlayerTank targetPlayer) {
        super(x, y, getSpeedByDifficulty(difficulty), getFireRateByDifficulty(difficulty), getHealthByDifficulty(difficulty));
        this.difficulty = difficulty;
        this.targetPlayer = targetPlayer;
        this.random = new Random();
        this.currentState = AIState.IDLE;
        this.stateChangeTime = System.currentTimeMillis();
        this.patrolDirection = random.nextInt(4);
        this.lastDirectionChange = System.currentTimeMillis();
    }
    
    // 根据难度获取速度
    private static int getSpeedByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY: return 4;    // 简单敌人速度快
            case MEDIUM: return 3;  // 中等敌人速度中等
            case HARD: return 2;    // 困难敌人速度慢
            default: return 3;
        }
    }
    
    // 根据难度获取射速
    private static int getFireRateByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY: return 1000; // 简单敌人射速慢
            case MEDIUM: return 700; // 中等敌人射速中等
            case HARD: return 500;  // 困难敌人射速快
            default: return 700;
        }
    }
    
    // 根据难度获取生命值
    private static int getHealthByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY: return 2;    // 简单敌人生命值低
            case MEDIUM: return 3;  // 中等敌人生命值中等
            case HARD: return 5;    // 困难敌人生命值高
            default: return 3;
        }
    }
    
    @Override
    public void move() {
        updateAIState();
        
        switch (currentState) {
            case PATROLLING:
                patrolMove();
                break;
            case CHASING:
                chaseMove();
                break;
            case ATTACKING:
                attackMove();
                break;
            case AVOIDING:
                avoidMove();
                break;
            default: // IDLE
                idleMove();
                break;
        }
        
        // 边界检查
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 640 - width) x = 640 - width;
        if (y > 480 - height) y = 480 - height;
    }
    
    @Override
    public Bullet fire() {
        // 只有在攻击状态下才会主动射击
        if (currentState == AIState.ATTACKING) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFireTime >= fireRate) {
                lastFireTime = currentTime;
                
                int bulletX = x + width / 2 - 4;
                int bulletY = y + height / 2 - 4;
                
                return new Bullet(bulletX, bulletY, direction, 6, getDamageByDifficulty(), this);
            }
        }
        return null;
    }
    
    // 根据难度获取伤害值
    private int getDamageByDifficulty() {
        switch (difficulty) {
            case EASY: return 1;
            case MEDIUM: return 2;
            case HARD: return 3;
            default: return 1;
        }
    }
    
    // 更新AI状态
    private void updateAIState() {
        long currentTime = System.currentTimeMillis();
        
        // 状态持续时间检查
        if (currentTime - stateChangeTime > getStateDuration()) {
            // 根据难度决定状态转换逻辑
            switch (difficulty) {
                case EASY:
                    easyAIStateTransition();
                    break;
                case MEDIUM:
                    mediumAIStateTransition();
                    break;
                case HARD:
                    hardAIStateTransition();
                    break;
            }
            stateChangeTime = currentTime;
        }
        
        // 困难敌人特殊能力：检测玩家并进入追逐状态
        if (difficulty == Difficulty.HARD && isPlayerInSight() && currentState != AIState.ATTACKING) {
            currentState = AIState.CHASING;
            stateChangeTime = currentTime;
        }
    }
    
    // 简单AI状态转换
    private void easyAIStateTransition() {
        // 简单敌人主要在巡逻和空闲状态切换
        if (random.nextDouble() < 0.7) {
            currentState = AIState.PATROLLING;
            patrolDirection = random.nextInt(4);
        } else {
            currentState = AIState.IDLE;
        }
    }
    
    // 中等AI状态转换
    private void mediumAIStateTransition() {
        // 中等敌人有一定几率追逐玩家
        double rand = random.nextDouble();
        if (rand < 0.5) {
            currentState = AIState.PATROLLING;
            patrolDirection = random.nextInt(4);
        } else if (rand < 0.8 && isPlayerNear()) {
            currentState = AIState.CHASING;
        } else {
            currentState = AIState.IDLE;
        }
    }
    
    // 困难AI状态转换
    private void hardAIStateTransition() {
        // 困难敌人更倾向于追逐和攻击玩家
        if (isPlayerNear()) {
            if (isPlayerInSight()) {
                currentState = AIState.ATTACKING;
            } else {
                currentState = AIState.CHASING;
            }
        } else if (random.nextDouble() < 0.6) {
            currentState = AIState.PATROLLING;
            patrolDirection = random.nextInt(4);
        } else {
            currentState = AIState.IDLE;
        }
    }
    
    // 获取当前状态持续时间
    private long getStateDuration() {
        switch (currentState) {
            case IDLE: return random.nextInt(1000) + 1000; // 1-2秒
            case PATROLLING: return random.nextInt(2000) + 2000; // 2-4秒
            case CHASING: return 3000; // 3秒
            case ATTACKING: return 2000; // 2秒
            case AVOIDING: return 1000; // 1秒
            default: return 1000;
        }
    }
    
    // 空闲状态移动
    private void idleMove() {
        // 空闲状态下偶尔小幅度移动
        if (random.nextDouble() < 0.1) {
            direction = random.nextInt(4);
            moveInDirection(direction);
        }
    }
    
    // 巡逻状态移动
    private void patrolMove() {
        // 定期改变巡逻方向
        if (System.currentTimeMillis() - lastDirectionChange > 1500) {
            patrolDirection = random.nextInt(4);
            lastDirectionChange = System.currentTimeMillis();
        }
        direction = patrolDirection;
        moveInDirection(direction);
    }
    
    // 追逐状态移动
    private void chaseMove() {
        if (targetPlayer == null) return;
        
        // 简单的追逐逻辑：向玩家方向移动
        if (x < targetPlayer.getX()) {
            direction = 1; // 右
        } else if (x > targetPlayer.getX()) {
            direction = 3; // 左
        }
        
        if (y < targetPlayer.getY()) {
            direction = 2; // 下
        } else if (y > targetPlayer.getY()) {
            direction = 0; // 上
        }
        
        moveInDirection(direction);
    }
    
    // 攻击状态移动
    private void attackMove() {
        // 攻击状态下保持方向并小幅调整位置
        if (random.nextDouble() < 0.3) {
            direction = random.nextInt(4);
        }
        moveInDirection(direction);
    }
    
    // 躲避状态移动
    private void avoidMove() {
        // 随机改变方向以躲避障碍物
        direction = random.nextInt(4);
        moveInDirection(direction);
    }
    
    // 向指定方向移动
    private void moveInDirection(int dir) {
        switch (dir) {
            case 0: y -= speed; break; // 上
            case 1: x += speed; break; // 右
            case 2: y += speed; break; // 下
            case 3: x -= speed; break; // 左
        }
    }
    
    // 检测玩家是否在附近
    private boolean isPlayerNear() {
        if (targetPlayer == null) return false;
        
        int distance = (int) Math.sqrt(
            Math.pow(x - targetPlayer.getX(), 2) + 
            Math.pow(y - targetPlayer.getY(), 2)
        );
        return distance < 200; // 200像素范围内
    }
    
    // 检测玩家是否在视线范围内（简单实现）
    private boolean isPlayerInSight() {
        if (targetPlayer == null) return false;
        
        // 简化实现：假设在同一行或同一列且距离适中
        boolean sameRow = Math.abs(y - targetPlayer.getY()) < 10;
        boolean sameCol = Math.abs(x - targetPlayer.getX()) < 10;
        return (sameRow || sameCol) && isPlayerNear();
    }
    
    // 获取敌人难度
    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    // 检查是否有穿墙能力
    public boolean canPassThroughWalls() {
        return difficulty == Difficulty.MEDIUM && random.nextDouble() < 0.1 || 
               difficulty == Difficulty.HARD && random.nextDouble() < 0.3;
    }
}