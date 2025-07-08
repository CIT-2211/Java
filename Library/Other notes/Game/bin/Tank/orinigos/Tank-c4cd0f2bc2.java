import java.awt.Rectangle;

/**
 * 坦克基类
 * 所有坦克的抽象父类，定义坦克的基本属性和行为
 */
public abstract class Tank {
    // 位置坐标
    protected int x;
    protected int y;
    
    // 方向 (0:上, 1:右, 2:下, 3:左)
    protected int direction;
    
    // 移动速度
    protected int speed;
    
    // 射击速度(毫秒/发)
    protected int fireRate;
    
    // 生命值
    protected int health;
    
    // 坦克宽度和高度(像素)
    protected int width;
    protected int height;
    
    // 上次射击时间
    protected long lastFireTime;
    
    /**
     * 构造方法
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param speed 移动速度
     * @param fireRate 射击速度
     * @param health 初始生命值
     */
    public Tank(int x, int y, int speed, int fireRate, int health) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.fireRate = fireRate;
        this.health = health;
        this.direction = 0; // 默认向上
        this.width = 32;    // 默认宽度
        this.height = 32;   // 默认高度
        this.lastFireTime = 0;
    }
    
    /**
     * 移动坦克
     */
    public abstract void move();
    
    /**
     * 射击方法
     * @return 新创建的子弹，如果不能射击则返回null
     */
    public abstract Bullet fire();
    
    /**
     * 受到伤害
     * @param damage 伤害值
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    
    /**
     * 判断坦克是否存活
     * @return 存活状态
     */
    public boolean isAlive() {
        return health > 0;
    }
    
    /**
     * 获取坦克的碰撞矩形
     * @return 碰撞矩形
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    // Getters and Setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }
    
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
}