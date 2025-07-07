import java.awt.Rectangle;

/**
 * 子弹类
 * 处理坦克发射的子弹逻辑，包括移动和碰撞检测
 */
public class Bullet {
    // 子弹位置
    private int x;
    private int y;
    
    // 子弹方向 (0:上, 1:右, 2:下, 3:左)
    private int direction;
    
    // 子弹速度
    private int speed;
    
    // 子弹伤害值
    private int damage;
    
    // 子弹宽度和高度
    private int width;
    private int height;
    
    // 子弹所有者（玩家或敌人坦克）
    private Tank owner;
    
    // 子弹是否活跃
    private boolean active;
    
    /**
     * 构造方法
     * @param x 初始X坐标
     * @param y 初始Y坐标
     * @param direction 方向
     * @param speed 速度
     * @param damage 伤害值
     * @param owner 发射者坦克
     */
    public Bullet(int x, int y, int direction, int speed, int damage, Tank owner) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.damage = damage;
        this.owner = owner;
        this.width = 8;  // 子弹宽度
        this.height = 8; // 子弹高度
        this.active = true;
    }
    
    /**
     * 移动子弹
     */
    public void move() {
        if (!active) return;
        
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
        
        // 检查是否超出地图边界
        if (x < 0 || x > 640 || y < 0 || y > 480) {
            active = false;
        }
    }
    
    /**
     * 获取子弹的碰撞矩形
     * @return 碰撞矩形
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * 检查子弹是否与坦克碰撞
     * @param tank 要检查的坦克
     * @return 是否碰撞
     */
    public boolean collidesWith(Tank tank) {
        // 防止子弹击中发射者自己
        if (tank == owner) return false;
        
        return getBounds().intersects(tank.getBounds());
    }
    
    /**
     * 检查子弹是否与墙体碰撞
     * @param wall 要检查的墙体
     * @return 是否碰撞
     */
    public boolean collidesWith(Wall wall) {
        return getBounds().intersects(wall.getBounds());
    }
    
    // Getters and Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getDamage() { return damage; }
    public Tank getOwner() { return owner; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}