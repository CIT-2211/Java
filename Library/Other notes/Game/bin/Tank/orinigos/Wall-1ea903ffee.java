import java.awt.Rectangle;

/**
 * 墙体类
 * 表示游戏中的各种障碍物，提供碰撞检测和破坏逻辑
 */
public class Wall {
    // 墙体类型枚举
    public enum Type {
        BRICK,   // 砖块墙：可破坏
        STEEL,   // 钢墙：不可破坏
        WATER,   // 水域：不可通过
        FOREST   // 森林：提供隐蔽
    }
    
    private Type type;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean destructible;
    private boolean passable;
    
    /**
     * 构造方法
     * @param type 墙体类型
     * @param x X坐标
     * @param y Y坐标
     */
    public Wall(Type type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = 32;  // 墙体宽度（瓦片大小）
        this.height = 32; // 墙体高度（瓦片大小）
        
        // 根据墙体类型设置属性
        switch (type) {
            case BRICK:
                destructible = true;
                passable = false;
                break;
            case STEEL:
                destructible = false;
                passable = false;
                break;
            case WATER:
                destructible = false;
                passable = false;
                break;
            case FOREST:
                destructible = false;
                passable = true;  // 森林可以通过，但提供隐蔽
                break;
        }
    }
    
    /**
     * 获取墙体的碰撞矩形
     * @return 碰撞矩形
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    /**
     * 墙体受到攻击
     * @param damage 攻击伤害值
     * @return 是否被摧毁
     */
    public boolean takeDamage(int damage) {
        if (destructible) {
            // 对于可破坏墙体，假设1点伤害即可摧毁
            return true;
        }
        return false;
    }
    
    // Getters and Setters
    public Type getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isDestructible() { return destructible; }
    public boolean isPassable() { return passable; }
}