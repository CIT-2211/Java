import java.awt.Rectangle;
import java.util.Random;

/**
 * 道具类
 * 实现游戏中的各种道具效果，包括生命恢复、速度提升等
 */
public class PowerUp {
    // 道具类型枚举
    public enum Type {
        HEALTH,        // 生命恢复
        SPEED,         // 速度提升
        FIRE_RATE,     // 射速提升
        SCORE_BONUS,   // 分数加成
        EXTRA_BULLET   // 额外子弹
    }
    
    private Type type;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean active;
    private long spawnTime;
    private static final int DURATION = 10000; // 道具持续时间(毫秒)
    
    /**
     * 构造方法
     * @param type 道具类型
     * @param x X坐标
     * @param y Y坐标
     */
    public PowerUp(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = 24;
        this.height = 24;
        this.active = true;
        this.spawnTime = System.currentTimeMillis();
    }
    
    /**
     * 随机生成道具
     * @param x X坐标
     * @param y Y坐标
     * @return 随机类型的道具
     */
    public static PowerUp createRandomPowerUp(int x, int y) {
        Random random = new Random();
        Type[] types = Type.values();
        Type randomType = types[random.nextInt(types.length)];
        return new PowerUp(randomType, x, y);
    }
    
    /**
     * 更新道具状态
     * 检查道具是否过期
     */
    public void update() {
        if (System.currentTimeMillis() - spawnTime > DURATION) {
            active = false;
        }
    }
    
    /**
     * 获取道具的碰撞矩形
     * @return 碰撞矩形
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * 应用道具效果到玩家坦克
     * @param player 玩家坦克
     */
    public void applyEffect(PlayerTank player) {
        switch (type) {
            case HEALTH:
                player.setHealth(Math.min(player.getHealth() + 1, 5)); // 最大生命值5
                break;
            case SPEED:
                player.setSpeed(player.getSpeed() + 2); // 速度+2
                break;
            case FIRE_RATE:
                // 射速提升(减少射击间隔)
                // 假设PlayerTank有setFireRate方法
                break;
            case SCORE_BONUS:
                player.addScore(100); // 增加100分
                break;
            case EXTRA_BULLET:
                // 实现额外子弹逻辑
                // 假设PlayerTank有setExtraBullet方法
                break;
        }
        active = false; // 道具被拾取后失效
    }
    
    // Getters and Setters
    public Type getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}