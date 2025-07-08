/**
 * 玩家坦克类
 * 继承自Tank基类，实现玩家控制的坦克功能
 */
public class PlayerTank extends Tank {
    // 玩家ID (1或2)
    private int playerId;
    
    // 玩家分数
    private int score;
    
    // 最大生命值
    private int maxHealth;
    
    // 是否有护盾
    private boolean hasShield;
    
    // 护盾持续时间
    private long shieldEndTime;
    
    /**
     * 构造方法
     * @param playerId 玩家ID (1或2)
     * @param x 初始X坐标
     * @param y 初始Y坐标
     */
    public PlayerTank(int playerId, int x, int y) {
        super(x, y, 3, 500, 3); // 速度3，射速500ms，生命值3
        this.playerId = playerId;
        this.score = 0;
        this.maxHealth = 3;
        this.hasShield = false;
        this.shieldEndTime = 0;
    }
    
    @Override
    public void move() {
        // 根据当前方向移动坦克
        switch (direction) {
            case 0: // 上
                y -= speed;
                break;
            case 1: // 右
                x += speed;
                break;
            case 2: // 下
                y += speed;
                break;
            case 3: // 左
                x -= speed;
                break;
        }
        
        // 边界检查，确保坦克不会移出地图
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        // 假设地图宽度为640，高度为480
        if (x > 640 - width) x = 640 - width;
        if (y > 480 - height) y = 480 - height;
    }
    
    @Override
    public Bullet fire() {
        long currentTime = System.currentTimeMillis();
        // 检查是否可以射击（射击间隔）
        if (currentTime - lastFireTime >= fireRate) {
            lastFireTime = currentTime;
            
            // 根据坦克当前方向和位置计算子弹初始位置
            int bulletX = x + width / 2 - 4; // 子弹宽度假设为8
            int bulletY = y + height / 2 - 4; // 子弹高度假设为8
            
            // 创建新子弹，伤害值为1
            return new Bullet(bulletX, bulletY, direction, 5, 1, this);
        }
        return null;
    }
    
    /**
     * 增加分数
     * @param points 增加的分数
     */
    public void addScore(int points) {
        score += points;
    }
    
    /**
     * 应用道具效果
     * @param powerUp 道具
     */
    public void applyPowerUp(PowerUp powerUp) {
        switch (powerUp.getType()) {
            case HEALTH:
                if (health < maxHealth) {
                    health++;
                }
                break;
            case SPEED:
                speed += 2; // 速度提升
                break;
            case FIRE_RATE:
                fireRate = Math.max(200, fireRate - 150); // 射速提升，最低200ms
                break;
            case SCORE_BONUS:
                addScore(100);
                break;
            case EXTRA_BULLET:
                // 实现多子弹逻辑
                break;
        }
    }
    
    /**
     * 更新护盾状态
     */
    public void updateShield() {
        if (hasShield && System.currentTimeMillis() > shieldEndTime) {
            hasShield = false;
        }
    }
    
    @Override
    public void takeDamage(int damage) {
        if (hasShield) {
            return; // 有护盾时不受伤害
        }
        super.takeDamage(damage);
    }
    
    // Getters and Setters
    public int getPlayerId() { return playerId; }
    public int getScore() { return score; }
    public boolean hasShield() { return hasShield; }
    
    public void setShield(boolean hasShield, int duration) {
        this.hasShield = hasShield;
        if (hasShield) {
            this.shieldEndTime = System.currentTimeMillis() + duration;
        }
    }
}