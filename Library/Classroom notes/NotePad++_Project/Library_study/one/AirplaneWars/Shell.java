import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
//子弹类

public class Shell extends GameObject {
    double degree; // 弧度值（0-2π）
    Random random = new Random();
    
    @Override
    public void drawMySelf(Graphics g) {
        // 1. 绘制子弹
        g.drawImage(img, (int)x, (int)y, width, height, null);
        
        // 2. 使用三角函数更新位置
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);  // 关键：使用sin不是signum
        
        // 3. 添加调试输出
        System.out.printf("位置: (%.1f, %.1f) | 弧度: %.2f | cos: %.2f | sin: %.2f%n",
                          x, y, degree, Math.cos(degree), Math.sin(degree));
        
        // 4. 边界检测（可选）
        if (x < 0 || x > 930 || y < 0 || y > 1200) {
            // 重置位置和方向
            resetPosition();
        }
    }
    
    private void resetPosition() {
        // 从屏幕边缘重新生成
        x = (random.nextDouble() > 0.5) ? 0 : 930;
        y = random.nextDouble() * 1200;
        
        // 指向屏幕中心
        double centerX = 930 / 2.0;
        double centerY = 1200 / 2.0;
        degree = Math.atan2(centerY - y, centerX - x);
    }
    
    public Shell(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
        // 确保设置随机弧度
        degree = Math.random() * Math.PI * 2;
        System.out.println("创建子弹，弧度=" + degree);
    }
}