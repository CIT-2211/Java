import java.util.Random;

/**
 * 游戏地图类
 * 负责生成和管理游戏地图，包括墙体和道具的分布
 */
public class GameMap {
    // 地图尺寸（瓦片数量）
    public static final int MAP_WIDTH = 40;   // 宽度（瓦片数）
    public static final int MAP_HEIGHT = 30;  // 高度（瓦片数）
    public static final int TILE_SIZE = 16;   // 瓦片大小（像素）
    
    // 墙体数组
    private Wall[][] walls;
    
    // 随机数生成器
    private Random random;
    
    /**
     * 构造方法
     */
    public GameMap() {
        walls = new Wall[MAP_WIDTH][MAP_HEIGHT];
        random = new Random();
        generateMap();
    }
    
    /**
     * 生成随机地图
     */
    public void generateMap() {
        // 清空现有地图
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                walls[x][y] = null;
            }
        }
        
        // 生成边界墙体
        generateBoundaryWalls();
        
        // 随机生成墙体
        generateRandomWalls();
        
        // 确保玩家出生区域安全
        clearPlayerSpawnArea();
        
        // 确保基地区域安全
        clearBaseArea();
    }
    
    /**
     * 生成边界墙体
     */
    private void generateBoundaryWalls() {
        // 上边界
        for (int x = 0; x < MAP_WIDTH; x++) {
            walls[x][0] = new Wall(Wall.Type.STEEL, x * TILE_SIZE, 0);
        }
        
        // 下边界
        for (int x = 0; x < MAP_WIDTH; x++) {
            walls[x][MAP_HEIGHT - 1] = new Wall(Wall.Type.STEEL, x * TILE_SIZE, (MAP_HEIGHT - 1) * TILE_SIZE);
        }
        
        // 左边界
        for (int y = 1; y < MAP_HEIGHT - 1; y++) {
            walls[0][y] = new Wall(Wall.Type.STEEL, 0, y * TILE_SIZE);
        }
        
        // 右边界
        for (int y = 1; y < MAP_HEIGHT - 1; y++) {
            walls[MAP_WIDTH - 1][y] = new Wall(Wall.Type.STEEL, (MAP_WIDTH - 1) * TILE_SIZE, y * TILE_SIZE);
        }
    }
    
    /**
     * 随机生成墙体
     */
    private void generateRandomWalls() {
        for (int x = 1; x < MAP_WIDTH - 1; x++) {
            for (int y = 1; y < MAP_HEIGHT - 1; y++) {
                // 跳过玩家出生区域和基地区域
                if (isInPlayerSpawnArea(x, y) || isInBaseArea(x, y)) {
                    continue;
                }
                
                // 随机生成墙体
                double rand = random.nextDouble();
                
                if (rand < 0.2) { // 20% 概率生成砖块墙
                    walls[x][y] = new Wall(Wall.Type.BRICK, x * TILE_SIZE, y * TILE_SIZE);
                } else if (rand < 0.3) { // 10% 概率生成钢墙
                    walls[x][y] = new Wall(Wall.Type.STEEL, x * TILE_SIZE, y * TILE_SIZE);
                } else if (rand < 0.4) { // 10% 概率生成水域
                    walls[x][y] = new Wall(Wall.Type.WATER, x * TILE_SIZE, y * TILE_SIZE);
                } else if (rand < 0.5) { // 10% 概率生成森林
                    walls[x][y] = new Wall(Wall.Type.FOREST, x * TILE_SIZE, y * TILE_SIZE);
                }
                // 50% 概率不生成墙体
            }
        }
    }
    
    /**
     * 清空玩家出生区域的墙体
     * 玩家1出生在左下角，玩家2出生在右下角
     */
    private void clearPlayerSpawnArea() {
        // 玩家1出生区域 (左下角 4x4 区域)
        clearArea(1, MAP_HEIGHT - 5, 4, 4);
        
        // 玩家2出生区域 (右下角 4x4 区域)
        clearArea(MAP_WIDTH - 5, MAP_HEIGHT - 5, 4, 4);
    }
    
    /**
     * 清空基地区域的墙体
     * 基地位于地图上方中央
     */
    private void clearBaseArea() {
        clearArea(MAP_WIDTH / 2 - 2, 1, 4, 4);
    }
    
    /**
     * 清空指定区域的墙体
     * @param startX 起始X坐标（瓦片）
     * @param startY 起始Y坐标（瓦片）
     * @param width 宽度（瓦片数）
     * @param height 高度（瓦片数）
     */
    private void clearArea(int startX, int startY, int width, int height) {
        for (int x = startX; x < startX + width && x < MAP_WIDTH; x++) {
            for (int y = startY; y < startY + height && y < MAP_HEIGHT; y++) {
                if (x >= 0 && y >= 0) {
                    walls[x][y] = null;
                }
            }
        }
    }
    
    /**
     * 检查指定位置是否在玩家出生区域
     * @param x X坐标（瓦片）
     * @param y Y坐标（瓦片）
     * @return 是否在玩家出生区域
     */
    private boolean isInPlayerSpawnArea(int x, int y) {
        // 玩家1出生区域
        boolean inPlayer1Area = x >= 1 && x < 5 && y >= MAP_HEIGHT - 5 && y < MAP_HEIGHT - 1;
        // 玩家2出生区域
        boolean inPlayer2Area = x >= MAP_WIDTH - 5 && x < MAP_WIDTH - 1 && y >= MAP_HEIGHT - 5 && y < MAP_HEIGHT - 1;
        
        return inPlayer1Area || inPlayer2Area;
    }
    
    /**
     * 检查指定位置是否在基地区域
     * @param x X坐标（瓦片）
     * @param y Y坐标（瓦片）
     * @return 是否在基地区域
     */
    private boolean isInBaseArea(int x, int y) {
        return x >= MAP_WIDTH / 2 - 2 && x < MAP_WIDTH / 2 + 2 && y >= 1 && y < 5;
    }
    
    /**
     * 获取指定像素位置的墙体
     * @param pixelX X像素坐标
     * @param pixelY Y像素坐标
     * @return 墙体对象，如果没有墙体则返回null
     */
    public Wall getWallAtPixel(int pixelX, int pixelY) {
        int tileX = pixelX / TILE_SIZE;
        int tileY = pixelY / TILE_SIZE;
        
        if (tileX >= 0 && tileX < MAP_WIDTH && tileY >= 0 && tileY < MAP_HEIGHT) {
            return walls[tileX][tileY];
        }
        return null;
    }
    
    /**
     * 获取指定瓦片位置的墙体
     * @param tileX X瓦片坐标
     * @param tileY Y瓦片坐标
     * @return 墙体对象，如果没有墙体则返回null
     */
    public Wall getWallAtTile(int tileX, int tileY) {
        if (tileX >= 0 && tileX < MAP_WIDTH && tileY >= 0 && tileY < MAP_HEIGHT) {
            return walls[tileX][tileY];
        }
        return null;
    }
    
    /**
     * 移除指定位置的墙体（用于墙体被摧毁的情况）
     * @param pixelX X像素坐标
     * @param pixelY Y像素坐标
     */
    public void removeWallAtPixel(int pixelX, int pixelY) {
        int tileX = pixelX / TILE_SIZE;
        int tileY = pixelY / TILE_SIZE;
        
        if (tileX >= 0 && tileX < MAP_WIDTH && tileY >= 0 && tileY < MAP_HEIGHT) {
            walls[tileX][tileY] = null;
        }
    }
    
    // Getters
    public int getMapWidthInPixels() {
        return MAP_WIDTH * TILE_SIZE;
    }
    
    public int getMapHeightInPixels() {
        return MAP_HEIGHT * TILE_SIZE;
    }
    
    public Wall[][] getWalls() {
        return walls;
    }
}